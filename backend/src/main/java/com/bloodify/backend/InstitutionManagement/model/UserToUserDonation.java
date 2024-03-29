package com.bloodify.backend.InstitutionManagement.model;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    private Integer userToUserDonID;

    @Column(nullable = false)
    @Size(min = 14, max = 14, message = "National ID is not 14 characters long")
    private String donorNationalID;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private User acceptor;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Institution institution;

    @Column(nullable = false)
    private LocalDate donationDate;

    public UserToUserDonation(Institution institution, User acceptor, String nationalID, LocalDate donationDate) {
        this.institution = institution;
        this.donorNationalID = nationalID;
        this.acceptor = acceptor;
        this.donationDate = donationDate;
    }
}
