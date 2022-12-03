package com.bloodify.backend.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SignupException  extends RuntimeException{
    SignupException(String msg){
        super(msg);
    }
}
