package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface UserToInstDonRepository extends JpaRepository<UserToInstDonation, Integer> {
    List<UserToInstDonation> findByInstitution_Email(@NonNull String email);

    List<UserToInstDonation> findByInstitution_EmailAndDonationDate(@NonNull String email,
                                                                    @NonNull LocalDate donationDate);

    List<UserToInstDonation> findByDonorNationalID(@NonNull String donorNationalID);

    int countByBloodTypeLike(String bloodType);

    @Query("select count(u) from UserToInstDonation u where u.bloodType like ?1 and u.donationDate between ?2 and ?3 and u.institution.email like ?4")
    int countByBloodTypeLikeAndDonationDateBetween(String bloodType, LocalDate donationDateStart, LocalDate donationDateEnd, String instEmail);






}