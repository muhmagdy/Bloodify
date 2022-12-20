package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.InstitutionManagement.dto.UserToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidDonorNID;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InvalidInstitution;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.PostAcceptorNotFound;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserToUserDonModelMapperTest {

    // mocking the dao responsible for checking the existence of the institution
    @Mock
    InstitutionDAO institutionDAO;

    // mocking the argument of the mapToModel method
    @Mock
    UserToUserDonDTO dto;

    // mocking the service responsible for getting the acceptor user from the post
    @Mock
    PostService postService;

    @InjectMocks
    UserToUserDonModelMapper mapper;

    private final RandomUserGenerations randomizer = new RandomUserGenerations();

    /**
     * tests that an error is thrown if the donor NID was null
     */
    @Test
    public void nullDonor() {
        when(dto.getDonorNationalID())
                .thenReturn(null);

        Assertions.assertThrows(InvalidDonorNID.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that an error is thrown if the donor NID length was less than 14 characters (13)
     */
    @Test
    public void invalidDonor() {
        when(dto.getDonorNationalID())
                .thenReturn("2343123212331");

        Assertions.assertThrows(InvalidDonorNID.class,
                () -> mapper.mapToModel(dto));

    }

    /**
     * tests that an error is thrown if no acceptor (in the post) was found
     * valid donor NID
     */
    @Test
    public void noAcceptorFound() {
        when(dto.getDonorNationalID())
                .thenReturn(randomizer.generateNationalID());

        when(postService.getReceiverFromPost(dto.getPostID()))
                .thenReturn(null);

        Assertions.assertThrows(PostAcceptorNotFound.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that an error is thrown if the institution was not found in the database
     * valid donor NID
     * acceptor found
     */
    @Test
    public void invalidInstitution() {
        when(dto.getDonorNationalID())
                .thenReturn(randomizer.generateNationalID());

        when(postService.getReceiverFromPost(dto.getPostID()))
                .thenReturn(new User());

        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(null);

        Assertions.assertThrows(InvalidInstitution.class,
                () -> mapper.mapToModel(dto));
    }

    /**
     * tests that no error is thrown if everything is correct
     * valid donor NID
     * acceptor found
     * valid institution
     */
    @Test
    public void validEverything() {
        when(dto.getDonorNationalID())
                .thenReturn(randomizer.generateNationalID());

        when(postService.getReceiverFromPost(dto.getPostID()))
                .thenReturn(new User());

        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        Assertions.assertDoesNotThrow(() -> mapper.mapToModel(dto));
    }

}