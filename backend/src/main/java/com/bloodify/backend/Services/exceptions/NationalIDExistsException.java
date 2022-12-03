package com.bloodify.backend.services.exceptions;

public class NationalIdExistsException extends RuntimeException{
    public NationalIdExistsException() {
        super("NationalIdExistsException");
    }
}
