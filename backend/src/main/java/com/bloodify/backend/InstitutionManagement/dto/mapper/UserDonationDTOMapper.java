package com.bloodify.backend.InstitutionManagement.dto.mapper;

import com.bloodify.backend.InstitutionManagement.controller.request.UserDonationRequest;
import com.bloodify.backend.InstitutionManagement.dto.UserDonationDTO;

public class UserDonationDTOMapper {
    public UserDonationDTO mapToDTO(UserDonationRequest userDonationRequest, String email) {
        UserDonationDTO userDonationDTO = new UserDonationDTO();

        userDonationDTO.setPostID(
                userDonationRequest.getPostID()
        );

        userDonationDTO.setInstitutionEmail(email);

        userDonationDTO.setDonorNationalID(
                userDonationRequest.getDonorNationalID()
        );

        return userDonationDTO;
    }
}
