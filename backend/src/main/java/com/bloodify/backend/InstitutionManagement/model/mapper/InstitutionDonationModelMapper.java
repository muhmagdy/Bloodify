package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.InstitutionManagement.dto.InstitutionDonationDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidAcceptorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidBloodBagsCount;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import com.bloodify.backend.InstitutionManagement.model.InstitutionDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InstitutionDonationModelMapper {
    @Autowired
    InstitutionDAO institutionDAO;


    public InstitutionDonation mapToModel(InstitutionDonationDTO institutionDonationDTO) {
        InstitutionDonation institutionDonation = new InstitutionDonation();

        institutionDonation.setInstitution(
                institutionDAO.findInstitutionByEmail(institutionDonationDTO.getInstitutionEmail())
        );

        if (institutionDonation.getInstitution() == null)
            throw new InvalidInstitution();

        if(institutionDonationDTO.getAcceptorNationalID() == null ||
                institutionDonationDTO.getAcceptorNationalID().length() != 14)
            throw new InvalidAcceptorNID();

        institutionDonation.setAcceptorNationalID(
                institutionDonationDTO.getAcceptorNationalID()
        );

        institutionDonation.setBloodType(
                institutionDonationDTO.getBloodType()
        );

        if(institutionDonationDTO.getBagsCount() == null ||
                institutionDonationDTO.getBagsCount() < 1)
            throw new InvalidBloodBagsCount();

        institutionDonation.setBagsCount(
                institutionDonationDTO.getBagsCount()
        );

        institutionDonation.setTransactionDate(
                LocalDate.now()
        );

        return institutionDonation;
    }

}
