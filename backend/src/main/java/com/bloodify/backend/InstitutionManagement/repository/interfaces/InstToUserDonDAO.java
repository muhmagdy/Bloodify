package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface InstToUserDonDAO {

    // save the institution to user donation
    boolean save(@NonNull InstToUserDonation instToUserDonation);

    // finds all the (institution to user) donations where the institution specified (by its string)
    // was part of
    List<InstToUserDonation> findByInstitutionEmail(@NonNull String email);

    // finds all the (institution to user) donations where the institution specified (by its ID)
    // was part of and that happened at a certain date
    List<InstToUserDonation> findByInstitutionEmailAndDate(@NonNull String email,
                                                           @NonNull LocalDate transactionDate);

}
