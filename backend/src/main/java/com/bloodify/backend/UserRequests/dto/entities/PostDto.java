package com.bloodify.backend.UserRequests.dto.entities;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    String userEmail;
    int institutionID;
    int requiredBags;
    BloodType bloodType;
}
