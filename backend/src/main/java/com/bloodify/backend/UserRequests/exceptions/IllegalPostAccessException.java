package com.bloodify.backend.UserRequests.exceptions;

public class IllegalPostAccessException extends RuntimeException{
    public IllegalPostAccessException() {
        super("illegal access");
    }
}
