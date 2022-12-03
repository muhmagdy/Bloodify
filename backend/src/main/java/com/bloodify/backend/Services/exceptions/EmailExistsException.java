package com.bloodify.backend.services.exceptions;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException() {
        super("EmailExistsException");
    }
}
