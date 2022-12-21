package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;

import java.util.List;

public interface CompatiblePosts {
    public Double distance(Double lat1, Double long1, Double lat2, Double long2);
    public boolean isPostMatchingUserDistance(User user, Post post, int threshold);
}
