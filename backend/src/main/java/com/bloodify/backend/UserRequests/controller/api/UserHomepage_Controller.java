package com.bloodify.backend.UserRequests.controller.api;


import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.service.interfaces.CompatiblePosts;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/displayingUserHome")

public class UserHomepage_Controller {
    @Autowired
    UserDAO userDAO;
    @Autowired
    PostDao postDao;
    @Autowired
    CompatiblePosts compatiblePosts;

    @GetMapping("/get_status/{email}")
    public int getUserStatus(@PathVariable("email") String userEmail){
        User user = userDAO.findUserByEmail(userEmail);
        return user.getStatus();
    }

    //  Status = 0
    @GetMapping("/get_posts_status0/{email}")
    public List<Post> getCompatiblePosts(@PathVariable("email") String userEmail){
        User user = userDAO.findUserByEmail(userEmail);
        return compatiblePosts.allPostsMatching(user, 5);
    }

    //  Status = 1
    @GetMapping("/get_posts_status1/{email}")
    public List<Post> getRequesterPosts(@PathVariable("email") String userEmail) {
        return postDao.getUserAllPosts(userEmail);
    }

    //  Status = 1
    @GetMapping("/get_accepting_Users/{email}")
    public List<User> getPossibleDonors(@PathVariable("email") String userEmail) {
        User user = userDAO.findUserByEmail(userEmail);
        return postDao.findAcceptingUsersByRequester(user);
    }

    //  Status = 2
    @GetMapping("/get_requester_info/{email}")
    public User getRequestingUserInfo(@PathVariable("email") String userEmail) {
        User user = userDAO.findUserByEmail(userEmail);
        return userDAO.findAcceptedPostByAcceptor(user).getUser();
    }

    //  Status = 2
    @GetMapping("/get_request_info/{email}")
    public Post getRequestInfo(@PathVariable("email") String userEmail) {
        User user = userDAO.findUserByEmail(userEmail);
        return userDAO.findAcceptedPostByAcceptor(user);
    }


}
