package com.bloodify.backend.UserRequests.service.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){super("user not registered exception");}
}
