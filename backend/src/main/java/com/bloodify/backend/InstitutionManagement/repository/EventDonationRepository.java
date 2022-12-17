package com.bloodify.backend.InstitutionManagement.repository;

import com.bloodify.backend.InstitutionManagement.model.entities.EventDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface EventDonationRepository extends JpaRepository<EventDonation, Integer> {

    // finds all the (user to institutions) donations that took place in the specified event (by id)
    List<EventDonation> findByEvent_EventID(@NonNull Integer eventID);

    // finds all the (user to institutions) donations that took place in the specified event (by id)
    // and during a specific date
    List<EventDonation> findByEvent_EventIDAndTransactionDate(@NonNull Integer eventID,
                                                              @NonNull LocalDate transactionDate);


    // finds all the (user to institutions) donations where the specified user (by NID) was a donor
    List<EventDonation> findByDonor_NationalID(@NonNull String nationalID);

}