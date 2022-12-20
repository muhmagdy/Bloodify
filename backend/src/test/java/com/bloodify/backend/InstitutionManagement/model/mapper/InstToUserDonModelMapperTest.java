package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.InstitutionManagement.dto.InstToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidAcceptorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidBloodBagsCount;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstToUserDonModelMapperTest {

    // mocking the dao responsible for checking the existence of the institution
    @Mock
    InstitutionDAO institutionDAO;

    // mocking the argument of the mapToModel method
    @Mock
    InstToUserDonDTO dto;

    @InjectMocks
    InstToUserDonModelMapper mapper;

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
     * tests that an error is thrown if the acceptor NID was null
     * institution exists
     */
    @Test
    public void nullAcceptorNID() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        when(dto.getAcceptorNationalID())
                .thenReturn(null);

        Assertions.assertThrows(InvalidAcceptorNID.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that an error is thrown if the acceptor NID length was less than 14 characters (13)
     * institution exists
     */
    @Test
    public void invalidAcceptorNID() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        when(dto.getAcceptorNationalID())
                .thenReturn("2343123212331");

        Assertions.assertThrows(InvalidAcceptorNID.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that an error is thrown if the blood bags count was less than 1
     * institution exists
     * valid acceptor NID
     */
    @Test
    public void lessThanOneBloodBagsCount() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        when(dto.getAcceptorNationalID())
                .thenReturn(randomizer.generateNationalID());

        when(dto.getBagsCount())
                .thenReturn(randomizer.generateCount(Integer.MIN_VALUE, 0));

        Assertions.assertThrows(InvalidBloodBagsCount.class,
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

        when(dto.getAcceptorNationalID())
                .thenReturn(randomizer.generateNationalID());

        when(dto.getBagsCount())
                .thenReturn(randomizer.generateCount(1, Integer.MAX_VALUE));

        Assertions.assertDoesNotThrow(() -> mapper.mapToModel(dto));
    }


}