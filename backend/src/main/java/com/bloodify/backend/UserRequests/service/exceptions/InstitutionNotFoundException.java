package com.bloodify.backend.UserRequests.service.exceptions;

public class InstitutionNotFoundException extends RuntimeException{
    public InstitutionNotFoundException(){super("institution not registered exception");}
}
