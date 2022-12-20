package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.InstitutionManagement.dto.UserToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidDonorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.PostAcceptorNotFound;
import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserToUserDonModelMapper {
    @Autowired
    PostService postService;

    @Autowired
    InstitutionDAO institutionDAO;

    public UserToUserDonation mapToModel(UserToUserDonDTO userToUserDonDTO) {
        UserToUserDonation userToUserDonation = new UserToUserDonation();

        if (userToUserDonDTO.getDonorNationalID() == null
                || userToUserDonDTO.getDonorNationalID().length() != 14)
            throw new InvalidDonorNID();

        userToUserDonation.setDonorNationalID(
                userToUserDonDTO.getDonorNationalID()
        );

        userToUserDonation.setAcceptor(
                postService.getReceiverFromPost(userToUserDonDTO.getPostID())
        );

        if (userToUserDonation.getAcceptor() == null)
            throw new PostAcceptorNotFound();

        userToUserDonation.setInstitution(
                institutionDAO.findInstitutionByEmail(userToUserDonDTO.getInstitutionEmail())
        );

        if (userToUserDonation.getInstitution() == null)
            throw new InvalidInstitution();

        userToUserDonation.setDonationDate(LocalDate.now());

        return userToUserDonation;
    }

}
