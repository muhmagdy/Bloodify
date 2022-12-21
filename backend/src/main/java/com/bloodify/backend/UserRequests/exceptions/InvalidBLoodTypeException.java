package com.bloodify.backend.UserRequests.exceptions;

public class InvalidBLoodTypeException extends RuntimeException{
    public InvalidBLoodTypeException() {
        super("invalid blood type");
    }
}
