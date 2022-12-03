package com.bloodify.backend.model.requests;


import java.time.LocalDate;

public class UserSignUpRequest {
    private String firstName;
    private String lastName;
    private String nationalID;
    private String email;
    private String bloodType;
    private char bloodTypeSign;
    private boolean hasDiseases;
    private LocalDate lastTimeDonated;
    private String password;
}
