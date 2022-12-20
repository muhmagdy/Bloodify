package com.bloodify.backend.InstitutionManagement.dto.mapper;

import com.bloodify.backend.InstitutionManagement.controller.request.UserToUserDonRequest;
import com.bloodify.backend.InstitutionManagement.dto.UserToUserDonDTO;

public class UserToUserDonDTOMapper {
    public UserToUserDonDTO mapToDTO(UserToUserDonRequest userToUserDonRequest, String email) {
        UserToUserDonDTO userToUserDonDTO = new UserToUserDonDTO();

        userToUserDonDTO.setPostID(
                userToUserDonRequest.getPostID()
        );

        userToUserDonDTO.setInstitutionEmail(email);

        userToUserDonDTO.setDonorNationalID(
                userToUserDonRequest.getDonorNationalID()
        );

        return userToUserDonDTO;
    }
}
