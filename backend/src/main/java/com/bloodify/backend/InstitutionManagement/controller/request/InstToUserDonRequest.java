package com.bloodify.backend.InstitutionManagement.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstToUserDonRequest {
    private String acceptorNationalID;

    private String bloodType;

    private Integer bagsCount;

}
