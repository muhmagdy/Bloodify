package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.InstitutionManagement.dto.UserDonationDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidDonorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.PostAcceptorNotFound;
import com.bloodify.backend.InstitutionManagement.model.UserDonation;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDonationModelMapper {
    @Autowired
    PostService postService;

    @Autowired
    InstitutionDAO institutionDAO;

    public UserDonation mapToModel(UserDonationDTO userDonationDTO) {
        UserDonation userDonation = new UserDonation();

        if (userDonationDTO.getDonorNationalID() == null
                || userDonationDTO.getDonorNationalID().length() != 14)
            throw new InvalidDonorNID();

        userDonation.setDonorNationalID(
                userDonationDTO.getDonorNationalID()
        );

        userDonation.setAcceptor(
                postService.getReceiverFromPost(userDonationDTO.getPostID())
        );

        if (userDonation.getAcceptor() == null)
            throw new PostAcceptorNotFound();

        userDonation.setInstitution(
                institutionDAO.findInstitutionByEmail(userDonationDTO.getInstitutionEmail())
        );

        if (userDonation.getInstitution() == null)
            throw new InvalidInstitution();

        userDonation.setDonationDate(LocalDate.now());

        return userDonation;
    }

}
