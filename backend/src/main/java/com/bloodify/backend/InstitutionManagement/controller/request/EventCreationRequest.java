package com.bloodify.backend.InstitutionManagement.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventCreationRequest {
    private String title;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @JsonFormat(pattern = "hh:mm")
    private LocalTime startWorkingHour;

    @JsonFormat(pattern = "hh:mm")
    private LocalTime endWorkingHour;

    private String location;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String description;
}
