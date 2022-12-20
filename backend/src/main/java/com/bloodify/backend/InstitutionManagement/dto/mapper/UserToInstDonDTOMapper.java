package com.bloodify.backend.InstitutionManagement.dto.mapper;

import com.bloodify.backend.InstitutionManagement.controller.request.UserToInstDonRequest;
import com.bloodify.backend.InstitutionManagement.dto.UserToInstDonDTO;

public class UserToInstDonDTOMapper {
    public UserToInstDonDTO mapToDTO(UserToInstDonRequest userToInstDonRequest, String email) {
        UserToInstDonDTO userToInstDonDTO = new UserToInstDonDTO();

        userToInstDonDTO.setInstitutionEmail(email);

        userToInstDonDTO.setDonorNationalID(
                userToInstDonRequest.getDonorNationalID()
        );

        return userToInstDonDTO;
    }
}
