package com.bloodify.backend.InstitutionManagement.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventID;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Institution institution;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime startWorkingHour;

    @Column(nullable = false)
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime endWorkingHour;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal longitude;

    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal latitude;

    @Column(length = 200)
    private String description;

}
