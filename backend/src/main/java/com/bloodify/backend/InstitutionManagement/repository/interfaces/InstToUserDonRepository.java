package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InstToUserDonRepository extends JpaRepository<InstToUserDonation, Integer> {

    List<InstToUserDonation> findByInstitution_Email(String email);


    List<InstToUserDonation> findByInstitution_EmailAndTransactionDate(@NonNull String email,
                                                                       @NonNull LocalDate transactionDate);

}