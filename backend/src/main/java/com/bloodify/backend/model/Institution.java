package com.bloodify.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
@NoArgsConstructor
public class Institution {
    private int institutionID;
    private String email;
    private String locationEnglish;
    private float locationLatitude;
    private float locationLongitude;
    float workingHours;

}
