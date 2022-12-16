package com.bloodify.backend.UserRequests.controller.request.entity;

import lombok.Data;

@Data
public class PostRequest {
    String userEmail;
    int institutionID;
    int requiredBags;
    String bloodType;

    public PostRequest(String userEmail, int institutionID, int requiredBags, String bloodType) {
        this.userEmail = userEmail;
        this.institutionID = institutionID;
        this.requiredBags = requiredBags;
        this.bloodType = bloodType;
    }
}
