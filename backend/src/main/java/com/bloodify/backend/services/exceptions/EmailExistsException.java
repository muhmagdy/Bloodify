package com.bloodify.backend.services.exceptions;

public class EmailExistsException extends SignupDuplicateException {
    public EmailExistsException() {
        super("An account already exists with the same email.");
    }
}
