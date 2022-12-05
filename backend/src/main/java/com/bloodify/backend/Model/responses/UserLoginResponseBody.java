package com.bloodify.backend.model.responses;

import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;

import lombok.Data;


@Data
public class UserLoginResponseBody {
    private String first_name,last_name, email, token,nationalID,institutionName,bloodType;
    boolean hasDiseases;

    public UserLoginResponseBody(User user, String token){
        this.email = user.getEmail();
        this.first_name = user.getFirstName() ;
        this.last_name= user.getLastName();
        this.nationalID=user.getNationalID();
        this.hasDiseases=user.isHasDiseases();
        this.bloodType=user.getBloodType();
        this.token = token;
    }

    public UserLoginResponseBody(Institution inst, String token){
        this.email = inst.getEmail();
        this.institutionName = inst.getName();
        this.token = token;
    }

}
