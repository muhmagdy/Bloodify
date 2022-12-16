package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.model.entities.Post;

import java.util.List;

public interface PostService {
    boolean savePost(PostDto dto);
    boolean updatePost(PostDto dto, int postID);
    boolean deletePost(PostDto dto);
    List<PostRequest> getUserPosts(String userEmail);
    Post getSpecificPost(String userEmail, int institutionID, String BloodType);

    void deleteRedundantPosts();
    List<User> getUsersToBeNotified(Post AcceptedPost);
}
