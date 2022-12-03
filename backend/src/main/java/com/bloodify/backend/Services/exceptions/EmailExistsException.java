package com.bloodify.backend.services.exceptions;

public class EmailExistsException extends SignupException{
    public EmailExistsException() {
        super("An account already exists with the same email.");
    }
}
