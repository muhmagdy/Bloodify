package com.bloodify.backend.InstitutionManagement.repository.classes;

import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InstToUserDonDAOImp implements InstToUserDonDAO {

    @Autowired
    InstToUserDonRepository instToUserDonRepository;

    @Override
    public boolean save(InstToUserDonation instToUserDonation) {
        try {
            instToUserDonRepository.save(instToUserDonation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<InstToUserDonation> findByInstitutionEmail(String email) {
        return instToUserDonRepository.findByInstitution_Email(email);
    }

    @Override
    public List<InstToUserDonation> findByInstitutionEmailAndDate(String email, LocalDate transactionDate) {
        return instToUserDonRepository.findByInstitution_EmailAndTransactionDate(
                email,
                transactionDate
        );
    }

    @Override
    public int requestedBloodBagsByTypeAndDate(String bloodType, LocalDate startDate, LocalDate endDate, String instEmail) {
        try {
            return instToUserDonRepository.addByBloodTypeLikeAndTransactionDateBetween(bloodType, startDate, endDate, instEmail);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
