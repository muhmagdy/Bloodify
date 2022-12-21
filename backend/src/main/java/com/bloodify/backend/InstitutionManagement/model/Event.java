package com.bloodify.backend.InstitutionManagement.model;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"institution"})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventID;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Institution institution;

    @Column(nullable = false)
    @Size(min = 5, max = 30)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalTime startWorkingHour;

    @Column(nullable = false)
    private LocalTime endWorkingHour;

    @Column(nullable = false)
    @Size(min = 5, max = 50)
    private String location;

    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal longitude;

    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal latitude;

}
