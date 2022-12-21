package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface UserToInstDonDAO {

    // saves the user to institution donation
    boolean save(UserToInstDonation userToInstDonation);

    // finds all the (user to institution) donations that took place
    // in the specified institution (by email)
    List<UserToInstDonation> findByInstitutionEmail(@NonNull String email);

    // finds all the (user to institution) donations that took place
    // in the specified institution (by email) and at a certain date
    List<UserToInstDonation> findByInstitutionEmailAndDate(@NonNull String email,
                                                           @NonNull LocalDate donationDate);

    // finds all the (user to institution) donations where the specified user (by NID) was a donor
    List<UserToInstDonation> findByDonorNID(@NonNull String nationalID);

}
