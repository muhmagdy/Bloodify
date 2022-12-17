package com.bloodify.backend.UserRequests.exceptions;

public class InstitutionNotFoundException extends RuntimeException{
    public InstitutionNotFoundException(){super("institution not registered exception");}
}
