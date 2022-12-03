package com.bloodify.backend.services.exceptions;

public class NationalIdExistsException extends SignupException{
    public NationalIdExistsException() {
        super("An account already exists with the same National ID.");
    }
}
