package com.bloodify.backend.UserRequests.service;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.model.response.PostBrief;
import com.bloodify.backend.UserRequests.model.response.UserBrief;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;
import com.bloodify.backend.UserRequests.service.entities.CompatiblePostsImp;
import com.bloodify.backend.UserRequests.service.interfaces.CompatiblePosts;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserHomePageService {
    @Resource(name = "userDAOImp")
    UserDAO userDAO;
    @Resource(name = "postDaoImp")
    PostDao postDao;
    @Autowired
    AcceptRepository acceptRepository;
    @Autowired
    CompatiblePosts compatiblePosts;

    // Check if has current post -> 1
    // Check if has accepted requests -> 2
    // Otherwise -> 0
    public int getUserStatus(String email, Double longitude, Double latitude, Double threshold){
        User user = userDAO.findUserByEmail(email);
        if(postDao.getUserAllPosts(email).size() > 0)
            return 1;
        else if(acceptRepository.findByUser(user).size() > 0)
            return 2;
        return 0;
    }

    ///TODO: Compatible types instead of exact
    public List<PostBrief> getCompatiblePosts(String email, Double longitude, Double latitude, Double threshold){
        User user = userDAO.findUserByEmail(email);
        List<Post> posts = postDao.getAllBloodTypePosts(user.getBloodType());
        List<PostBrief> postBriefs = new ArrayList<>();
        for(Post post: posts){
            User requester = post.getUser();
            Institution inst = post.getInstitution();
            Double distance = compatiblePosts.distance(latitude, longitude, inst.getLatitude(), inst.getLongitude());
            if(distance > threshold)
                continue;
            postBriefs.add(postToPostBrief(post, requester, distance));
        }
        return postBriefs;
    }

    public List<PostBrief> getPostsCreatedByUser(String email){
        return postListToPostBrief(postDao.getUserAllPosts(email));
    }

    public List<UserBrief> getDonorsOfPost(String email, int postId) throws Exception {
        Post post = postDao.getPostByID(postId);
        if(!post.getUser().getEmail().equals(email))
            throw new Exception("This post doesn't belong to the account!");
        return acceptedPostListToUserBrief(acceptRepository.findByPost(post));
    }

    public UserBrief getPostRequester(int postId, Double longitude, Double latitude, Double threshold){
        Post post = postDao.getPostByID(postId);
        User user = post.getUser();
        Institution inst = post.getInstitution();
        Double distance = compatiblePosts.distance(latitude, longitude, inst.getLatitude(), inst.getLongitude());
        return userToUserBrief(user, distance);
    }

    public List<PostBrief> getPostsAcceptedByUser(String email, Double longitude, Double latitude, Double threshold){
        User user = userDAO.findUserByEmail(email);
        List<PostBrief> postBriefs = new ArrayList<>();
        List<AcceptedPost> acceptedPosts = acceptRepository.findByUser(user);
        for(AcceptedPost acceptedPost: acceptedPosts){
            Post post = acceptedPost.getPost();
            User requester = post.getUser();
            Institution inst = post.getInstitution();
            Double distance = compatiblePosts.distance(latitude, longitude, inst.getLatitude(), inst.getLongitude());
            postBriefs.add(postToPostBrief(post, requester, distance));
        }
        return postBriefs;
    }

    List<PostBrief> postListToPostBrief(List<Post> posts){
        List<PostBrief> postBriefs = new ArrayList<>();
        for(Post post: posts){
            User user = post.getUser();
            postBriefs.add(postToPostBrief(post, user, 0.0));
        }
        return postBriefs;
    }

    List<UserBrief> userListToUserBrief(List<User> users){
        List<UserBrief> userBriefs = new ArrayList<>();
        for(User user: users){
            userBriefs.add(userToUserBrief(user, 0.0));
        }
        return userBriefs;
    }

    List<UserBrief> acceptedPostListToUserBrief(List<AcceptedPost> acceptedPosts){
        List<UserBrief> userBriefs = new ArrayList<>();
        for(AcceptedPost acceptedPost: acceptedPosts){
            User user = acceptedPost.getUser();
            userBriefs.add(
                    new UserBrief(user.getUserID(), user.getFirstName()+" "+user.getLastName()
                            , user.getBloodType(), 0.0)
            );
        }
        return userBriefs;
    }

    UserBrief userToUserBrief(User user, Double distance){
        return new UserBrief(user.getUserID(), user.getFirstName()+" "+user.getLastName()
                , user.getBloodType(), distance);
    }

    PostBrief postToPostBrief(Post post, User user, Double distance){
        return new PostBrief(post.getPostID(), user.getNationalID(), user.getFirstName()+" "+user.getLastName(),
                post.getStartTime(), post.getBagsNum(), post.getBloodType(), distance);
    }
}
