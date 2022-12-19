package com.bloodify.backend.InstitutionManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionDonationDTO {
    private String institutionEmail;

    private String acceptorNationalID;

    private String bloodType;

    private Integer bagsCount;
}
