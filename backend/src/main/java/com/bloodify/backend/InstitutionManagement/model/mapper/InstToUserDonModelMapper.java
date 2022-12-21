package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.InstitutionManagement.dto.InstToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidAcceptorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidBloodBagsCount;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InstToUserDonModelMapper {
    @Autowired
    InstitutionDAO institutionDAO;


    public InstToUserDonation mapToModel(InstToUserDonDTO instToUserDonDTO) {
        InstToUserDonation instToUserDonation = new InstToUserDonation();

        instToUserDonation.setInstitution(
                institutionDAO.findInstitutionByEmail(instToUserDonDTO.getInstitutionEmail())
        );

        if (instToUserDonation.getInstitution() == null)
            throw new InvalidInstitution();

        if (instToUserDonDTO.getAcceptorNationalID() == null ||
                instToUserDonDTO.getAcceptorNationalID().length() != 14)
            throw new InvalidAcceptorNID();

        instToUserDonation.setAcceptorNationalID(
                instToUserDonDTO.getAcceptorNationalID()
        );

        instToUserDonation.setBloodType(
                instToUserDonDTO.getBloodType()
        );

        if (instToUserDonDTO.getBagsCount() == null ||
                instToUserDonDTO.getBagsCount() < 1)
            throw new InvalidBloodBagsCount();

        instToUserDonation.setBagsCount(
                instToUserDonDTO.getBagsCount()
        );

        instToUserDonation.setTransactionDate(
                LocalDate.now()
        );

        return instToUserDonation;
    }

}
