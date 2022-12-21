package com.bloodify.backend.UserRequests.controller.api;


import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.model.request.AcceptPostRequest;
import com.bloodify.backend.UserRequests.model.response.PostBrief;
import com.bloodify.backend.UserRequests.model.response.Response;
import com.bloodify.backend.UserRequests.model.response.UserBrief;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;
import com.bloodify.backend.UserRequests.service.UserHomePageService;
import com.bloodify.backend.UserRequests.service.entities.CompatiblePostsImp;
import com.bloodify.backend.UserRequests.service.interfaces.CompatiblePosts;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/user")

public class UserHomepage_Controller {
    @Autowired
    UserHomePageService homePageService;

    @GetMapping("/status")
    public int getUserStatus(Authentication auth){
        return homePageService.getUserStatus(auth.getName(), 0.0, 0.0, 1000.0);
    }

    //  Status = 0
    @GetMapping("/posts/compatible")
    public List<PostBrief> getCompatiblePosts(Authentication auth, @RequestBody AcceptPostRequest acceptPostRequest){
        return homePageService.getCompatiblePosts(
                auth.getName(), acceptPostRequest.getLongitude(),
                acceptPostRequest.getLatitude(), acceptPostRequest.getThreshold());
    }

    //  Status = 1
    @GetMapping("/posts/requester")
    public List<PostBrief> getRequesterPosts(Authentication auth, @RequestBody AcceptPostRequest acceptPostRequest) {
        return homePageService.getPostsCreatedByUser(auth.getName());
    }

    //  Status = 1
    // return from new repo
    @GetMapping("/post/donors")
    public List<UserBrief> getAcceptedDonors(@RequestBody int id, Authentication auth) throws Exception {
        return homePageService.getDonorsOfPost(auth.getName(), id);
    }

    //  Status = 2
    // inside post
    @GetMapping("/post/requester")
    public UserBrief getRequestingUserInfo(Authentication auth, @RequestBody AcceptPostRequest acceptPostRequest) {
        return homePageService.getPostRequester(acceptPostRequest.getId(), acceptPostRequest.getLongitude(),
                acceptPostRequest.getLatitude(), acceptPostRequest.getThreshold());
    }

    @PostMapping("/post/accept")
    public Response acceptPost(@RequestBody AcceptPostRequest acceptPostRequest, Authentication auth){
        String email = auth.getName();
        if(homePageService.acceptRequest(
                email, acceptPostRequest.getId(), acceptPostRequest.getLongitude(),
                acceptPostRequest.getLatitude(), acceptPostRequest.getThreshold())){
            return new Response(true, "Success", null);
        }
        else {
            return new Response(false, "Error occurred while accepting the post", null);
        }
    }

//      Status = 2
    @GetMapping("/post/current")
    public List<PostBrief> getRequestInfo(Authentication auth, @RequestBody AcceptPostRequest acceptPostRequest) {
        return homePageService.getPostsAcceptedByUser(
                auth.getName(), acceptPostRequest.getLongitude(),
                acceptPostRequest.getLatitude(), acceptPostRequest.getThreshold());
    }


}
