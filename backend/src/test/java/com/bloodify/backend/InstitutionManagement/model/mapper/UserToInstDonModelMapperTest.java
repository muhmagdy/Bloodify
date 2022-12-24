package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.InstitutionManagement.dto.UserToInstDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidDonorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserToInstDonModelMapperTest {
    @Mock
    InstitutionDAO institutionDAO;

    @Mock
    UserToInstDonDTO dto;

    @InjectMocks
    UserToInstDonModelMapper mapper;

    private final RandomUserGenerations randomizer = new RandomUserGenerations();

    /**
     * tests that an error is thrown if the institution was not found in the database
     */
    @Test
    public void nullInstitution() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(null);

        Assertions.assertThrows(InvalidInstitution.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that an error is thrown if the donor NID was null
     * institution exists
     */
    @Test
    public void nullDonorNID() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        when(dto.getDonorNationalID())
                .thenReturn(null);

        Assertions.assertThrows(InvalidDonorNID.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that an error is thrown if the donor NID length was less than 14 characters (13)
     * institution exists
     */
    @Test
    public void invalidNID() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        when(dto.getDonorNationalID())
                .thenReturn(null);

        Assertions.assertThrows(InvalidDonorNID.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that no error is thrown if everything is correct
     * institution exists
     * valid acceptor NID
     * valid blood bags count
     */
    @Test
    public void validEverything() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        when(dto.getDonorNationalID())
                .thenReturn(randomizer.generateNationalID());

        Assertions.assertDoesNotThrow(() -> mapper.mapToModel(dto));
    }

}