package com.bloodify.backend.UserRequests.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordChangeRequest {
    String email;
    String code;
    String newPassword;
}
