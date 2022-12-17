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
public class UserDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userDonationId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User donor;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User acceptor;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Institution institution;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate donationDate;

}
