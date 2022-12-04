package com.bloodify.backend.services.exceptions;

public class BothEmailAndNationalIdExists extends SignupDuplicateException {
    public BothEmailAndNationalIdExists() {
        super("An account already exists with the same email and national id.");
    }
}
