package com.bloodify.backend.AccountManagement.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiseasesStatusResponse {
    private boolean status;
    private String message;
}
