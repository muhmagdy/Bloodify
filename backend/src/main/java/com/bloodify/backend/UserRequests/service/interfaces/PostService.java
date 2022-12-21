package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.model.entities.Post;

import java.util.List;

public interface PostService {
    boolean savePost(PostDto dto);
    boolean updatePost(PostDto dto);
    boolean deletePost(int postID, String userEmail);
    List<PostRequest> getUserPosts(String userEmail);
    Post getSpecificPost(String userEmail, int institutionID, String BloodType);
    List<User> getUsersToBeNotified(Post AcceptedPost);
    public List<User> getUsersToBeNotified(Post acceptedPost, Double instLongitude, Double instLatitude, int threshold);
    int getPostID(PostDto dto);
    User getReceiverFromPost(int postID);
    boolean decrementBags(int postID);
    Institution getInstitutionFromPost(int postID);
}
