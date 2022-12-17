package com.bloodify.backend.InstitutionManagement.repository;

import com.bloodify.backend.InstitutionManagement.model.entities.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {

    // finds all the (user to user) donations that took place in the specified institution (by id)
    List<UserDonation> findByInstitution_institutionID(int institutionID);

    // finds all the (user to user) donations that took place in the specified institution (by id)
    // and at a certain date
    List<UserDonation> findByInstitution_InstitutionIDAndDonationDate(int institutionID,
                                                                      @NonNull LocalDate donationDate);

    // finds all the (user to user) donations where the specified user (by NID) was a donor
    List<UserDonation> findByDonor_NationalID(@NonNull String nationalID);

    // finds all the (user to user) donations where the specified user (by NID) was an acceptor
    List<UserDonation> findByAcceptor_NationalID(@NonNull String nationalID);

}