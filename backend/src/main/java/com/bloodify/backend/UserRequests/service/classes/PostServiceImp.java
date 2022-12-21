package com.bloodify.backend.UserRequests.service.classes;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements PostService {
    @Override
    public User getReceiverFromPost(int postID) {
        return null;
    }

    @Override
    public boolean decrementBags(int postID) {
        return true;
    }
}
