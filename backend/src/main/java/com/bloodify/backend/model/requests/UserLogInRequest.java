package com.bloodify.backend.model.requests;

import lombok.Data;

@Data
public class UserLogInRequest {
    private String email;
    private String password;
}
