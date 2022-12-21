package com.bloodify.backend.UserRequests.exceptions;

public class DuplicatePostException extends RuntimeException{
    public DuplicatePostException() {
        super("the post is already found");
    }
}
