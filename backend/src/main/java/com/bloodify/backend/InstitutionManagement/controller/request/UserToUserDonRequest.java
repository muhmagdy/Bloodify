package com.bloodify.backend.InstitutionManagement.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToUserDonRequest {
    private Integer postID;

    private String donorNationalID;
}
