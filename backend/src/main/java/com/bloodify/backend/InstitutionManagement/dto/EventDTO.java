package com.bloodify.backend.InstitutionManagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
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
}
