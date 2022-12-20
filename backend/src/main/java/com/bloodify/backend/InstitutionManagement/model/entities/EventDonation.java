package com.bloodify.backend.InstitutionManagement.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EventDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventDonationID;

    private String donorNationalID;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Institution institution;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate transactionDate;

}