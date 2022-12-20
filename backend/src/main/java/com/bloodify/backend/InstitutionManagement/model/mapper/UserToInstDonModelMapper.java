package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.InstitutionManagement.dto.UserToInstDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidDonorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.PostAcceptorNotFound;
import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserToInstDonModelMapper {
    @Autowired
    InstitutionDAO institutionDAO;

    public UserToInstDonation mapToModel(UserToInstDonDTO userToInstDonDTO) {
        UserToInstDonation userToInstDonation = new UserToInstDonation();

        userToInstDonation.setInstitution(
                institutionDAO.findInstitutionByEmail(userToInstDonDTO.getInstitutionEmail())
        );

        if (userToInstDonation.getInstitution() == null)
            throw new InvalidInstitution();

        if (userToInstDonDTO.getDonorNationalID() == null
                || userToInstDonDTO.getDonorNationalID().length() != 14)
            throw new InvalidDonorNID();

        userToInstDonation.setDonorNationalID(
                userToInstDonDTO.getDonorNationalID()
        );

        userToInstDonation.setDonationDate(LocalDate.now());

        return userToInstDonation;
    }
}
