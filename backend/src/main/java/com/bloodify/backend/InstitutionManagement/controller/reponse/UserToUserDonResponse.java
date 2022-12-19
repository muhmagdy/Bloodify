package com.bloodify.backend.InstitutionManagement.controller.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserToUserDonResponse {
    private boolean state;

    private String message;

    private boolean updatedLastTimeDon;
}
