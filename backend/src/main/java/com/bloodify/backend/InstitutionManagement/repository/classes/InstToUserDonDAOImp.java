package com.bloodify.backend.InstitutionManagement.repository.classes;

import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InstToUserDonDAOImp implements InstToUserDonDAO {

    @Autowired
    InstToUserDonRepository institutionDonationRepo;

    @Override
    public boolean save(InstToUserDonation instToUserDonation) {
        try {
            institutionDonationRepo.save(instToUserDonation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<InstToUserDonation> findByInstitutionEmail(String email) {
        return institutionDonationRepo.findByInstitution_Email(email);
    }

    @Override
    public List<InstToUserDonation> findByInstitutionEmailAndDate(String email, LocalDate transactionDate) {
        return institutionDonationRepo.findByInstitution_EmailAndTransactionDate(
                email,
                transactionDate
        );
    }

}
