package com.bloodify.backend.AccountManagement.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SignUpResponse {
    private boolean state;
    private String message;
}
