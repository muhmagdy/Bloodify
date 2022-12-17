package com.bloodify.backend.InstitutionManagement.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.InstitutionManagement.model.entities.compositekey.EventDonationID;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@IdClass(EventDonationID.class)
@Getter
@Setter
public class EventDonation {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User donor;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Event event;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate transactionDate;

}