package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;

import java.util.List;

public interface CompatiblePosts {
    public List<Post> allPostsMatching (User user, double threshold);
}
