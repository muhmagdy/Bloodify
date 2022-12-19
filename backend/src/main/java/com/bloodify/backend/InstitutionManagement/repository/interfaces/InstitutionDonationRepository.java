package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.InstitutionDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InstitutionDonationRepository extends JpaRepository<InstitutionDonation, Integer> {

    List<InstitutionDonation> findByInstitution_Email(String email);


    List<InstitutionDonation> findByInstitution_EmailAndTransactionDate(@NonNull String email,
                                                                        @NonNull LocalDate transactionDate);

}