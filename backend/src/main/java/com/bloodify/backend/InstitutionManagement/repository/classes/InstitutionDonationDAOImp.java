package com.bloodify.backend.InstitutionManagement.repository.classes;

import com.bloodify.backend.InstitutionManagement.model.InstitutionDonation;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstitutionDonationDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstitutionDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InstitutionDonationDAOImp implements InstitutionDonationDAO {

    @Autowired
    InstitutionDonationRepository institutionDonationRepo;

    @Override
    public boolean save(InstitutionDonation institutionDonation) {
        try {
            institutionDonationRepo.save(institutionDonation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<InstitutionDonation> findByInstitutionEmail(String email) {
        return institutionDonationRepo.findByInstitution_Email(email);
    }

    @Override
    public List<InstitutionDonation> findByInstitutionEmailAndDate(String email, LocalDate transactionDate) {
        return institutionDonationRepo.findByInstitution_EmailAndTransactionDate(
                email,
                transactionDate
        );
    }

}
