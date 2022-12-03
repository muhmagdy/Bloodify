package com.bloodify.backend.services.exceptions;

public class NationalIdExistsException extends SignupDuplicateException {
    public NationalIdExistsException() {
        super("An account already exists with the same National ID.");
    }
}
