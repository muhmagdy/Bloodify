package com.bloodify.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NationalIdExistsException extends SignupException  {
    public NationalIdExistsException(){
        super("An account already exists with the same national id.");
    }
}