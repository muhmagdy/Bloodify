package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.InstitutionDonation;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface InstitutionDonationDAO {

    // save the institution to user donation
    boolean save(@NonNull InstitutionDonation institutionDonation);

    // finds all the (institution to user) donations where the institution specified (by its string)
    // was part of
    List<InstitutionDonation> findByInstitutionEmail(@NonNull String email);

    // finds all the (institution to user) donations where the institution specified (by its ID)
    // was part of and that happened at a certain date
    List<InstitutionDonation> findByInstitutionEmailAndDate(@NonNull String email,
                                                            @NonNull LocalDate transactionDate);

}
