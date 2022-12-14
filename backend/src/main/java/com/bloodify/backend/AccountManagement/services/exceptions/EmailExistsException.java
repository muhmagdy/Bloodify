package com.bloodify.backend.AccountManagement.services.exceptions;

public class EmailExistsException extends SignupDuplicateException {
    public EmailExistsException() {
        super("An account already exists with the same email.");
    }
}
