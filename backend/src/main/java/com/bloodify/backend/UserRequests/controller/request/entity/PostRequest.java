package com.bloodify.backend.UserRequests.controller.request.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostRequest {
    int postID;
    int institutionID;
    LocalDateTime LastUpdateTime;
    LocalDateTime expiryTime;
    int requiredBags;
    String bloodType;
}
