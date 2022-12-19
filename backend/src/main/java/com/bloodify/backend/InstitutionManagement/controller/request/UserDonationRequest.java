package com.bloodify.backend.InstitutionManagement.controller.request;

import lombok.Getter;

@Getter
public class UserDonationRequest {
    private Integer postID;

    private String donorNationalID;
}
