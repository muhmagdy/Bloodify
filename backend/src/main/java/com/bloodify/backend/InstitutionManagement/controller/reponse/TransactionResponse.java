package com.bloodify.backend.InstitutionManagement.controller.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {
    private boolean state;

    private String message;

}
