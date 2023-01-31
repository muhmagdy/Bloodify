package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.LoginSessionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.mappers.Post_PostRequestMapper;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.exceptions.IllegalPostAccessException;
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.model.mapper.Post_DTO_Mapper;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import com.bloodify.backend.notification.NotificationService;
import com.bloodify.backend.notification.dao.Interfaces.NotificationHistoryDAO;
import com.bloodify.backend.notification.model.NotificationHistory;
import com.bloodify.backend.notification.model.NotificationHistoryKey;
import com.bloodify.backend.notification.model.PushNotificationRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    @Autowired
    private AcceptRepository acceptRepository;

    public PostServiceImp(Post_DTO_Mapper postDtoMapper) {
        this.postDtoMapper = postDtoMapper;
    }

    @Autowired
    NotificationService notificationFacade;

    @Autowired
    NotificationHistoryDAO notificationHistoryDAO;

    @Override
    public boolean savePost(PostDto dto) {
        Post postToSave;
        try {
            postToSave = this.postDtoMapper.map_to_Post(dto);
            boolean status = this.postDao.addPost(postToSave);
            if (status) {
                List<User> users = this.getUsersToBeNotified(postToSave);
                for (User itr : users) {
                    PushNotificationRequest pushableNotification = new PushNotificationRequest("",
                            postToSave.getInstitution().getLongitude(),
                            postToSave.getInstitution().getLatitude(),
                            postToSave.getInstitution().getName(), false);
                    notificationFacade.sendNotification(itr.getEmail(), pushableNotification);
                    NotificationHistoryKey notificationHistoryKey = new NotificationHistoryKey(postToSave.getPostID(),
                            itr.getUserID());
                    NotificationHistory notificationHistory = new NotificationHistory(notificationHistoryKey, itr,
                            postToSave);
                    notificationHistoryDAO.Save(notificationHistory);
                }
            }
            return status;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePost(PostDto dto) {
        Post postToEdit;
        try {
            postToEdit = this.postDtoMapper.map_to_Post(dto);
            Post supposed = this.postDao.getPostByID(dto.getPostID());
            if (supposed == null)
                throw new PostNotFoundException();
            if (!supposed.getUser().getEmail().equals(dto.getUserEmail()))
                throw new IllegalPostAccessException();
            postToEdit.setPostID(dto.getPostID());
            return this.postDao.updatePost(postToEdit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePost(int postID, String userEmail) {
        Post postToDelete;
        try {
            postToDelete = this.postDao.getPostByID(postID);
            if (postToDelete != null && !postToDelete.getUser().getEmail().equals(userEmail))
                throw new IllegalPostAccessException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        acceptRepository.deleteByPost(postToDelete);
        return this.postDao.deleteSpecificUserPost(postToDelete);
    }

    @Override
    public List<PostRequest> getUserPosts(String userEmail) {
        List<Post> posts = this.postDao.getUserAllPosts(userEmail);
        if (posts.size() == 0)
            return new ArrayList<>();
        List<PostRequest> requestsToShow = new ArrayList<>();
        for (Post post : posts)
            requestsToShow.add(requestMapper.map_to_request(post));
        return requestsToShow;
    }

    @Override
    public Post getSpecificPost(String userEmail, int institutionID, String bloodType) {
        try {
            return this.postDao.getSpecificUserPost(userEmail, institutionID, bloodType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    boolean filterCriteria(User u, Post acceptedPost) {
        boolean b = !u.isHasDiseases() && u.getLastTimeDonated().isBefore(LocalDate.now().minusMonths(6)) &&
                acceptedPost.getUser().getUserID() != u.getUserID();

        System.out.println(b);
        return b;
    }

    @Override
    public List<User> getUsersToBeNotified(Post acceptedPost) {
        BloodTypeFactory factory = BloodTypeFactory.getFactory();
        BloodType type = factory.generateFromString(acceptedPost.getBloodType());
        List<BloodType> compatibleTypes = type.getCompatibleTypesPost();
        List<String> typesInString = new ArrayList<>();
        for (BloodType bType : compatibleTypes)
            typesInString.add(bType.toString());
        return (userDAO.findByBloodTypeIn(typesInString)).stream().filter(
                (User u) -> (filterCriteria(u, acceptedPost))).toList();
    }

    @Override
    public List<User> getUsersToBeNotified(Post acceptedPost, Double instLongitude, Double instLatitude,
            int threshold) {
        return null;
    }

    @Override
    public int getPostID(PostDto dto) {
        Post postToRetrieve;
        try {
            postToRetrieve = this.getSpecificPost(dto.getUserEmail(), dto.getInstitutionID(),
                    dto.getBloodType().toString());
            if (postToRetrieve == null)
                return -1;
            return postToRetrieve.getPostID();
        } catch (Exception e) {
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
            if (postToRetrieve == null)
                return null;
            return postToRetrieve.getUser();
        } catch (Exception e) {
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
            if (postToRetrieve.getBagsNum() == 0)
                this.postDao.deleteSpecificUserPost(postToRetrieve);
            else
                this.postDao.updatePost(postToRetrieve);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Institution getInstitutionFromPost(int postID) {
        Post postToRetrieve;
        try {
            postToRetrieve = this.postDao.getPostByID(postID);
            if (postToRetrieve == null)
                return null;
            return postToRetrieve.getInstitution();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Scheduled(fixedRate = 3600000)
    public void deleteOldPosts() {
        this.postDao.deleteUnnecessaryPosts();
    }
}
