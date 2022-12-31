package com.bloodify.backend.InstitutionManagement.model;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserToInstDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userToInstDonID;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    private Institution institution;

    @Column(nullable = false)
    @Size(min = 14, max = 14, message = "National ID is not 14 characters long")
    private String donorNationalID;

    @Column(nullable = false)
    @Size(max = 3, message = "Blood type is too long")
    private String bloodType;

    @Column(nullable = false)
    private LocalDate donationDate;

    public UserToInstDonation(Institution institution, String donorNationalID, String bloodType, LocalDate donationDate) {
        this.institution = institution;
        this.donorNationalID = donorNationalID;
        this.bloodType = bloodType;
        this.donationDate = donationDate;
    }
}
