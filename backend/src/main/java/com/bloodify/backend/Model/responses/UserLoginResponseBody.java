package com.bloodify.backend.model.responses;

import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;

import lombok.Data;


@Data
public class UserLoginResponseBody {
    private String name, email, token;

    public UserLoginResponseBody(User user, String token){
        this.email = user.getEmail();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.token = token;
    }

    public UserLoginResponseBody(Institution inst, String token){
        this.email = inst.getEmail();
        this.name = inst.getName();
        this.token = token;
    }

}
