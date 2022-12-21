package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserToUserDonRepository extends JpaRepository<UserToUserDonation, Integer> {
    List<UserToUserDonation> findByInstitution_Email(@NonNull String email);

    List<UserToUserDonation> findByInstitution_EmailAndDonationDate(@NonNull String email,
                                                                    @NonNull LocalDate donationDate);

    List<UserToUserDonation> findByDonorNationalID(@NonNull String donorNationalID);

    List<UserToUserDonation> findByAcceptor_NationalID(@NonNull String nationalID);

}