package com.bloodify.backend.InstitutionManagement.model;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class InstToUserDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instToUserDonID;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    private Institution institution;

    @Column(nullable = false)
    private String acceptorNationalID;

    @Column(nullable = false)
    @Size(max = 3, message = "Blood type is too long")
    private String bloodType;

    @Column(nullable = false)
    private Integer bagsCount;

    @Column(nullable = false)
    private LocalDate transactionDate;

}
