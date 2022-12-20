package com.bloodify.backend.InstitutionManagement.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstToUserDonRequest {
    private String acceptorNationalID;

    private String bloodType;

    private Integer bagsCount;

}
