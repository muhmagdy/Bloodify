package com.bloodify.backend.services.exceptions;

public class BothEmailAndNationalIdExists extends RuntimeException{
    public BothEmailAndNationalIdExists() {
        super("BothEmailAndNationalIdExists");
    }
}
