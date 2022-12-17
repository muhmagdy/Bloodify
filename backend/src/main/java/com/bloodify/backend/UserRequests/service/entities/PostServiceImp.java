package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.mappers.Post_PostRequestMapper;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.model.mapper.Post_DTO_Mapper;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private Post_DTO_Mapper postDtoMapper;
    @Autowired
    private Post_PostRequestMapper requestMapper;
    @Autowired
    private UserDAO userDAO;

    Criteria criteria = new Criteria();

    @Override
    public boolean savePost(PostDto dto) {
        Post postToSave = this.postDtoMapper.map_to_Post(dto);
        boolean status = this.postDao.addPost(postToSave);
        if (status) {
            List<User> users = this.getUsersToBeNotified(postToSave);
            userDAO.updateStatus(postToSave.getUser().getUserID(), 0);
            /****************  Notification Goes Here *****8****/
        }
        return status;
    }

    @Override
    public boolean updatePost(PostDto dto, int postID) {
        Post postToEdit = this.postDtoMapper.map_to_Post(dto);
        postToEdit.setPostID(postID);
        return this.postDao.updatePost(postToEdit);
    }

    @Override
    public boolean deletePost(PostDto dto) {
        Post postToDelete = this.postDtoMapper.map_to_Post(dto);
        return this.postDao.deleteSpecificUserPost(postToDelete);
    }

    @Override
    public List<PostRequest> getUserPosts(String userEmail) {
        List<Post> posts = this.postDao.getUserAllPosts(userEmail);
        if(posts == null || posts.size() == 0) return new ArrayList<>();
        List<PostRequest> requestsToShow = new ArrayList<>();
        for (Post post: posts) requestsToShow.add(requestMapper.map_to_request(post));
        return requestsToShow;
    }

    @Override
    public Post getSpecificPost(String userEmail, int institutionID, String bloodType) {
        BloodTypeFactory factory = BloodTypeFactory.getFactory();
        PostDto postDto = new PostDto(userEmail, institutionID, 0, factory.generateFromString(bloodType));
        return this.postDao.getSpecificUserPost(this.postDtoMapper.map_to_Post(postDto));
    }

    @Override
    public void deleteRedundantPosts() {
        this.postDao.deleteUnnecessaryPosts();
    }

    @Override
    public List<User> getUsersToBeNotified(Post acceptedPost) {
        BloodTypeFactory factory = BloodTypeFactory.getFactory();
        BloodType type = factory.generateFromString(acceptedPost.getBloodType());
        List<BloodType> compatibleTypes = type.getCompatibleTypes();
        List<String> typesInString = new ArrayList<>();
        for (BloodType bType: compatibleTypes) typesInString.add(bType.toString());
        return userDAO.findByBloodTypeIn(typesInString);
    }

    /**  Potential users are based on 3 criteria: matching blood type, user.status=0, distance < threshold  */
    @Override
    public List<User> getUsersToBeNotified(Post acceptedPost, Double instLongitude, Double instLatitude, int threshold) {
        List<User> matchingBloodType = criteria.getUsersMatchingWithPostBloodType(acceptedPost);
        List<User> potentialDonors = criteria.getPotentialDonorsOnStatus(0);
//      Get their intersection
        potentialDonors.retainAll(matchingBloodType);
        return criteria.filterOnDistance(potentialDonors, instLongitude, instLatitude, threshold);
    }

}
