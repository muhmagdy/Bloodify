package com.bloodify.backend.InstitutionManagement.dto.mapper;

import com.bloodify.backend.InstitutionManagement.controller.request.InstToUserDonRequest;
import com.bloodify.backend.InstitutionManagement.dto.InstToUserDonDTO;

public class InstToUserDonDTOMapper {
    public InstToUserDonDTO mapToDTO(InstToUserDonRequest instToUserDonRequest,
                                     String email) {

        InstToUserDonDTO instToUserDonDTO = new InstToUserDonDTO();

        instToUserDonDTO.setInstitutionEmail(email);

        instToUserDonDTO.setAcceptorNationalID(
                instToUserDonRequest.getAcceptorNationalID()
        );

        instToUserDonDTO.setBloodType(
                instToUserDonRequest.getBloodType()
        );

        instToUserDonDTO.setBagsCount(
                instToUserDonRequest.getBagsCount()
        );

        return instToUserDonDTO;
    }
}
