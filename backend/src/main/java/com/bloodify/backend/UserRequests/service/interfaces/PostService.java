package com.bloodify.backend.UserRequests.service.interfaces;


import com.bloodify.backend.AccountManagement.model.entities.User;

public interface PostService {

    User getReceiverFromPost(int postID);

    boolean decrementBags(int postID);
}