package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface UserToUserDonDAO {

    // saves the user to user donation
    boolean save(UserToUserDonation UserToUserDonation);

    // finds all the (user to user) donations that took place in the specified institution (by email)
    List<UserToUserDonation> findByInstitutionEmail(@NonNull String email);


    // finds all the (user to user) donations that took place in the specified institution (by email)
    // and at a certain date
    List<UserToUserDonation> findByInstitutionEmailAndDate(@NonNull String email,
                                                           @NonNull LocalDate donationDate);


    // finds all the (user to user) donations where the specified user (by NID) was a donor
    List<UserToUserDonation> findByDonorNID(@NonNull String nationalID);


    // finds all the (user to user) donations where the specified user (by NID) was an acceptor
    List<UserToUserDonation> findByAcceptorNID(@NonNull String nationalID);

    public int requestedBloodBagsByTypeAndDate (String bloodType, LocalDate start, LocalDate end, String instEmail);
}
