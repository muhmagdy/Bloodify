package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.UserDonation;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface UserDonationDAO {

    // saves the user to user donation
    boolean save(UserDonation UserDonation);

    // finds all the (user to user) donations that took place in the specified institution (by email)
    List<UserDonation> findByInstitutionEmail(@NonNull String email);


    // finds all the (user to user) donations that took place in the specified institution (by email)
    // and at a certain date
    List<UserDonation> findByInstitutionEmailAndDate(@NonNull String email,
                                                     @NonNull LocalDate donationDate);


    // finds all the (user to user) donations where the specified user (by NID) was a donor
    List<UserDonation> findByDonorNID(@NonNull String nationalID);


    // finds all the (user to user) donations where the specified user (by NID) was an acceptor
    List<UserDonation> findByAcceptorNID(@NonNull String nationalID);
}
