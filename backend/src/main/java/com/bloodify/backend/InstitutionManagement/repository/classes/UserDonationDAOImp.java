package com.bloodify.backend.InstitutionManagement.repository.classes;

import com.bloodify.backend.InstitutionManagement.model.UserDonation;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserDonationDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserDonationDAOImp implements UserDonationDAO {
    @Autowired
    private UserDonationRepository userDonationRepo;

    @Override
    public boolean save(UserDonation userDonation) {
        try {
            userDonationRepo.save(userDonation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<UserDonation> findByInstitutionEmail(String email) {
        return userDonationRepo.findByInstitution_Email(email);
    }

    @Override
    public List<UserDonation> findByInstitutionEmailAndDate(String email, LocalDate donationDate) {
        return userDonationRepo.findByInstitution_EmailAndDonationDate(email, donationDate);
    }

    @Override
    public List<UserDonation> findByDonorNID(String nationalID) {
        return userDonationRepo.findByDonorNationalID(nationalID);
    }

    @Override
    public List<UserDonation> findByAcceptorNID(String nationalID) {
        return userDonationRepo.findByAcceptor_NationalID(nationalID);
    }

}
