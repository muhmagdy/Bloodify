package com.bloodify.backend.InstitutionManagement.model;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserToUserDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userDonationId;

    private String donorNationalID;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User acceptor;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Institution institution;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate donationDate;

    public UserToUserDonation(String donorNationalID, User acceptor, Institution institution, LocalDate donationDate) {
        this.donorNationalID = donorNationalID;
        this.acceptor = acceptor;
        this.institution = institution;
        this.donationDate = donationDate;
    }
}
