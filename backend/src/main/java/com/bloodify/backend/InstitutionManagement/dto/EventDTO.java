package com.bloodify.backend.InstitutionManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EventDTO {
    private String title;

    private String institutionEmail;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startWorkingHour;

    private LocalTime endWorkingHour;

    private String location;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String description;
}
