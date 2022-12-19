package com.bloodify.backend.InstitutionManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDonationDTO {
    private Integer postID;

    private String institutionEmail;

    private String donorNationalID;
}
