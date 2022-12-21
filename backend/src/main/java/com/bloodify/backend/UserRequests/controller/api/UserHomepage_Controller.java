package com.bloodify.backend.UserRequests.controller.api;


import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.service.entities.CompatiblePostsImp;
import com.bloodify.backend.UserRequests.service.interfaces.CompatiblePosts;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/user")

public class UserHomepage_Controller {
//    @Autowired
    @Resource(name = "userDAOImp")
    UserDAO userDAO;
    @Resource(name = "postDaoImp")
    PostDao postDao;
    @Autowired
    CompatiblePostsImp compatiblePosts;

    @GetMapping("/status")
    public int getUserStatus(Authentication auth){
        User user = userDAO.findUserByEmail(auth.getName());
        return user.getStatus();
    }

    //  Status = 0
    @GetMapping("/posts/compatible")
    public List<Post> getCompatiblePosts(Authentication auth){
        System.out.println(auth.getName());
        User user = userDAO.findUserByEmail(auth.getName());
        List<Post> result = compatiblePosts.allPostsMatching(user, 3);
        for(Post p: result)
            System.out.println(p.toString());
        return result;
    }

    //  Status = 1
    @GetMapping("/posts/requester")
    public List<Post> getRequesterPosts(Authentication auth) {
        return postDao.getUserAllPosts(auth.getName());
    }

    //  Status = 1
    @GetMapping("/donors")
    public List<User> getPossibleDonors(Authentication auth) {
        User user = userDAO.findUserByEmail(auth.getName());
        return postDao.findAcceptingUsersByRequester(user);
    }

    //  Status = 2
    @GetMapping("/requester")
    public User getRequestingUserInfo(Authentication auth) {
        User user = userDAO.findUserByEmail(auth.getName());
        return userDAO.findAcceptedPostByAcceptor(user).getUser();
    }

    //  Status = 2
    @GetMapping("/post/current")
    public Post getRequestInfo(Authentication auth) {
        User user = userDAO.findUserByEmail(auth.getName());
        return userDAO.findAcceptedPostByAcceptor(user);
    }


}
