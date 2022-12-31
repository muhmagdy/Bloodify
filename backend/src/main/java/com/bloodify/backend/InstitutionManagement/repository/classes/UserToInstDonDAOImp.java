package com.bloodify.backend.InstitutionManagement.repository.classes;

import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserToInstDonDAOImp implements UserToInstDonDAO {

    @Autowired
    UserToInstDonRepository userToInstDonRepository;

    @Override
    public boolean save(UserToInstDonation userToInstDonation) {
        try {
            userToInstDonRepository.save(userToInstDonation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<UserToInstDonation> findByInstitutionEmail(String email) {
        return userToInstDonRepository.findByInstitution_Email(email);
    }

    @Override
    public List<UserToInstDonation> findByInstitutionEmailAndDate(String email, LocalDate donationDate) {
        return userToInstDonRepository.findByInstitution_EmailAndDonationDate(email, donationDate);
    }

    @Override
    public List<UserToInstDonation> findByDonorNID(String nationalID) {
        return userToInstDonRepository.findByDonorNationalID(nationalID);
    }

    @Override
    public int requestedBloodBagsByTypeAndDate(String bloodType, LocalDate startDate, LocalDate endDate, String instEmail){
        return userToInstDonRepository.countByBloodTypeLikeAndDonationDateBetween(bloodType, startDate, endDate, instEmail);
    }

}
