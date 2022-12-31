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
    private UserToUserDonRepository userToUserDonRepository;

    @Override
    public boolean save(UserToUserDonation userToUserDonation) {
        try {
            userToUserDonRepository.save(userToUserDonation);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<UserToUserDonation> findByInstitutionEmail(String email) {
        return userToUserDonRepository.findByInstitution_Email(email);
    }

    @Override
    public List<UserToUserDonation> findByInstitutionEmailAndDate(String email, LocalDate donationDate) {
        return userToUserDonRepository.findByInstitution_EmailAndDonationDate(email, donationDate);
    }

    @Override
    public List<UserToUserDonation> findByDonorNID(String nationalID) {
        return userToUserDonRepository.findByDonorNationalID(nationalID);
    }

    @Override
    public List<UserToUserDonation> findByAcceptorNID(String nationalID) {
        return userToUserDonRepository.findByAcceptor_NationalID(nationalID);
    }

    public int requestedBloodBagsByTypeAndDate (String bloodType, LocalDate start, LocalDate end, String instEmail) {
        return userToUserDonRepository.countByAcceptor_BloodTypeAndDonationDateBetween(bloodType, start, end, instEmail);
    }
}
