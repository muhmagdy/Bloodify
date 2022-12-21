package com.bloodify.backend.UserRequests.exceptions;

public class IllegalDateException extends RuntimeException{
    public IllegalDateException() {
        super("expiry date is before start date");
    }
}
