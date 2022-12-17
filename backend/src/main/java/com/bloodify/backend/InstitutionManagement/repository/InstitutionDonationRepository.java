package com.bloodify.backend.InstitutionManagement.repository;

import com.bloodify.backend.InstitutionManagement.model.entities.InstitutionDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface InstitutionDonationRepository extends JpaRepository<InstitutionDonation, Integer> {

    // finds all the (institution to user) donations where the institution specified (by its ID)
    // was part of
    List<InstitutionDonation> findByInstitution_InstitutionID(int institutionID);

    // finds all the (institution to user) donations where the institution specified (by its ID)
    // was part of and that happened at a certain date
    List<InstitutionDonation> findByInstitution_InstitutionIDAndTransactionDate(int institutionID,
                                                                                @NonNull LocalDate transactionDate);

    // finds all the (institution to user) donations where the specified user (by NID) was an acceptor
    List<InstitutionDonation> findByAcceptor_NationalID(@NonNull String nationalID);

}