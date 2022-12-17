package com.bloodify.backend.UserRequests.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){super("user not registered exception");}
}
