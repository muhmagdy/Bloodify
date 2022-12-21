package com.bloodify.backend.UserRequests.dto.entities;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDto {
    int postID;
    String userEmail;
    int institutionID;
    int requiredBags;
    BloodType bloodType;
    LocalDateTime creationTime;
    LocalDateTime expiryTime;

    public PostDto(int postID) {
        this.postID = postID;
    }
}
