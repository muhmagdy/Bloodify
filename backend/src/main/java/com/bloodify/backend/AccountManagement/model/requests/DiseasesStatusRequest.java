package com.bloodify.backend.AccountManagement.model.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DiseasesStatusRequest {
    private boolean hasDiseases;
}
