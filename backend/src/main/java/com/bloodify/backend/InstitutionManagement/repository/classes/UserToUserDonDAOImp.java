package com.bloodify.backend.InstitutionManagement.repository.classes;

import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToUserDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToUserDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserToUserDonDAOImp implements UserToUserDonDAO {
    @Autowired
    private UserToUserDonRepository userDonationRepo;

    @Override
    public boolean save(UserToUserDonation userToUserDonation) {
        try {
            userDonationRepo.save(userToUserDonation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<UserToUserDonation> findByInstitutionEmail(String email) {
        return userDonationRepo.findByInstitution_Email(email);
    }

    @Override
    public List<UserToUserDonation> findByInstitutionEmailAndDate(String email, LocalDate donationDate) {
        return userDonationRepo.findByInstitution_EmailAndDonationDate(email, donationDate);
    }

    @Override
    public List<UserToUserDonation> findByDonorNID(String nationalID) {
        return userDonationRepo.findByDonorNationalID(nationalID);
    }

    @Override
    public List<UserToUserDonation> findByAcceptorNID(String nationalID) {
        return userDonationRepo.findByAcceptor_NationalID(nationalID);
    }

}
