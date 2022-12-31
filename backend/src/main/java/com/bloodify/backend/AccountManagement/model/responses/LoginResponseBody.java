package com.bloodify.backend.AccountManagement.model.responses;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;

import lombok.Data;


@Data
public class LoginResponseBody {
    private String first_name,last_name, email, token,nationalID,institutionName,bloodType;
    private boolean hasDiseases;
    private int id;

    public LoginResponseBody(User user, String token){
        this.id = user.getUserID();
        this.email = user.getEmail();
        this.first_name = user.getFirstName() ;
        this.last_name= user.getLastName();
        this.nationalID=user.getNationalID();
        this.hasDiseases=user.isHasDiseases();
        this.bloodType=user.getBloodType();
        this.token = token;
    }

    public LoginResponseBody(Institution inst, String token){
        this.id = inst.getInstitutionID();
        this.email = inst.getEmail();
        this.institutionName = inst.getName();
        this.token = token;
    }

}
