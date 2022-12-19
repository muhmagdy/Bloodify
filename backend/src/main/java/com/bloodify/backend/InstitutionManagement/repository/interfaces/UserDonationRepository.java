package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {
    List<UserDonation> findByInstitution_Email(@NonNull String email);

    List<UserDonation> findByInstitution_EmailAndDonationDate(@NonNull String email,
                                                              @NonNull LocalDate donationDate);

    List<UserDonation> findByDonorNationalID(@NonNull String donorNationalID);

    List<UserDonation> findByAcceptor_NationalID(@NonNull String nationalID);

}