package com.bloodify.backend.UserRequests.exceptions;

public class NoBagsNeededException extends RuntimeException{
    public NoBagsNeededException() {
        super("bags number is not a +ve number");
    }
}
