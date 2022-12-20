package com.bloodify.backend.UserRequests.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException() {
        super("there is no such post");
    }
}
