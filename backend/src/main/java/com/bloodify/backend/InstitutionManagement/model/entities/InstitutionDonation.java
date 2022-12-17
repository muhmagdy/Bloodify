package com.bloodify.backend.InstitutionManagement.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class InstitutionDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer institutionDonationId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User acceptor;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Institution institution;

    // private enum BloodType ? will that help in statistics ?

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate transactionDate;

}
