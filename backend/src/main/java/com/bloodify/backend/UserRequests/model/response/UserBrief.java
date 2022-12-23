package com.bloodify.backend.UserRequests.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBrief {
    int userID;
    String name;
    String bloodType;
    Double distance;
}
