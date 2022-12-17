package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.mappers.Post_PostRequestMapper;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.model.mapper.Post_DTO_Mapper;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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


    public PostServiceImp(Post_DTO_Mapper postDtoMapper) {
        this.postDtoMapper = postDtoMapper;
    }

    @Override
    public boolean savePost(PostDto dto) {
        Post postToSave;
        try {
            postToSave = this.postDtoMapper.map_to_Post(dto);
            boolean status = this.postDao.addPost(postToSave);
            if (status) {
                List<User> users = this.getUsersToBeNotified(postToSave);
                /****************  Notification Goes Here *****8****/
            }
            return status;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePost(PostDto dto, int postID) {
        Post postToEdit;
        try{
            String userEmail = this.postDao.getPostEmail(postID);
            if (userEmail == null) throw new UserNotFoundException();
            if(!userEmail.equals(dto.getUserEmail())) throw new IllegalAccessException();
            postToEdit = this.postDtoMapper.map_to_Post(dto);
            postToEdit.setPostID(postID);
            return this.postDao.updatePost(postToEdit);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePost(PostDto dto) {
        Post postToDelete;
        try {
           postToDelete = this.postDtoMapper.map_to_Post(dto);
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return this.postDao.deleteSpecificUserPost(postToDelete);
    }

    @Override
    public List<PostRequest> getUserPosts(String userEmail) {
        List<Post> posts = this.postDao.getUserAllPosts(userEmail);
        if(posts.size() == 0) return new ArrayList<>();
        List<PostRequest> requestsToShow = new ArrayList<>();
        for (Post post: posts) requestsToShow.add(requestMapper.map_to_request(post));
        return requestsToShow;
    }

    @Override
    public Post getSpecificPost(String userEmail, int institutionID, String bloodType) {
        BloodTypeFactory factory = BloodTypeFactory.getFactory();
        PostDto postDto = new PostDto(userEmail, institutionID, 0, factory.generateFromString(bloodType));
        try {
            return this.postDao.getSpecificUserPost(this.postDtoMapper.map_to_Post(postDto));
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
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

    @Override
    public int getPostID(PostDto dto) {
        Post postToRetrieve;
        try {
            postToRetrieve = this.getSpecificPost(dto.getUserEmail(), dto.getInstitutionID(), dto.getBloodType().toString());
            if(postToRetrieve == null) return -1;
            return postToRetrieve.getPostID();
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public User getReceiverFromPost(int postID) {
        Post postToRetrieve;
        try {
            postToRetrieve = this.postDao.getPostByID(postID);
            if (postToRetrieve == null) return null;
            return postToRetrieve.getUser();
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean decrementBags(int postID) {
        Post postToRetrieve;
        try {
            postToRetrieve = this.postDao.getPostByID(postID);
            postToRetrieve.setBagsNum(postToRetrieve.getBagsNum() - 1);
            if (postToRetrieve.getBagsNum() == 0) this.postDao.deleteSpecificUserPost(postToRetrieve);
            else this.postDao.updatePost(postToRetrieve);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Institution getInstitutionFromPost(int postID){
        Post postToRetrieve;
        try {
            postToRetrieve = this.postDao.getPostByID(postID);
            if (postToRetrieve == null) return null;
            return postToRetrieve.getInstitution();
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Scheduled(fixedRate = 3600000)
    public void deleteOldPosts(){
        this.postDao.deleteUnnecessaryPosts();
    }
}
