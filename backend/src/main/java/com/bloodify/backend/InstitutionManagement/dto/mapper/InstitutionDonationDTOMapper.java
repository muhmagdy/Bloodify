package com.bloodify.backend.InstitutionManagement.dto.mapper;

import com.bloodify.backend.InstitutionManagement.controller.request.InstitutionDonationRequest;
import com.bloodify.backend.InstitutionManagement.dto.InstitutionDonationDTO;

public class InstitutionDonationDTOMapper {
    public InstitutionDonationDTO mapToDTO(InstitutionDonationRequest institutionDonationRequest,
                                           String email) {

        InstitutionDonationDTO institutionDonationDTO = new InstitutionDonationDTO();

        institutionDonationDTO.setInstitutionEmail(email);

        institutionDonationDTO.setAcceptorNationalID(
                institutionDonationRequest.getAcceptorNationalID()
        );

        institutionDonationDTO.setBloodType(
                institutionDonationRequest.getBloodType()
        );

        institutionDonationDTO.setBagsCount(
                institutionDonationRequest.getBagsCount()
        );

        return institutionDonationDTO;
    }
}
