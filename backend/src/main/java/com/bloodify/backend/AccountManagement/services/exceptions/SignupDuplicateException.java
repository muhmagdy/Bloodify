package com.bloodify.backend.AccountManagement.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class SignupDuplicateException extends RuntimeException{
    SignupDuplicateException(String msg){
        super(msg);
    }
}
