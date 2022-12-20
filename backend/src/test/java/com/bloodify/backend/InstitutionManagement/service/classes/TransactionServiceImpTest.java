package com.bloodify.backend.InstitutionManagement.service.classes;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.InstitutionManagement.dto.InstToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InsufficientBloodBags;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.TransactionException;
import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import com.bloodify.backend.InstitutionManagement.model.mapper.InstToUserDonModelMapper;
import com.bloodify.backend.InstitutionManagement.model.mapper.UserToUserDonModelMapper;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToUserDonDAO;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Random;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransactionServiceImpTest {
    @Mock
    UserDAO userDAO;

    @Mock
    UserToUserDonDAO userToUserDonDAO;

    @Mock
    UserToUserDonDTO userToUserDonDTO;

    @Mock
    UserToUserDonModelMapper userToUserDonModelMapper;

    @Mock
    UserToUserDonation userToUserDonation;

    @Mock
    PostService postService;

    @Mock
    InstitutionDAO instDAO;

    @Mock
    InstToUserDonDAO instToUserDonDAO;

    @Mock
    InstToUserDonDTO instToUserDonDTO;

    @Mock
    InstToUserDonModelMapper instToUserDonModelMapper;

    @Mock
    InstToUserDonation instToUserDonation;

    @InjectMocks
    TransactionServiceImp transactionService;

    final static int BOUNDARY = 1000;
    static int smallBagsCount;
    static int largeBagsCount;

    @BeforeAll
    public static void randomConstructor() {
        Random random = new Random();
        smallBagsCount = random.nextInt(BOUNDARY);
        largeBagsCount = random.nextInt(BOUNDARY) + BOUNDARY + 1;
    }

    /**
     * tests that an exception is thrown if something wrong happened while saving the record in the database
     * decrementing post blood bags -> success
     */
    @Test
    public void savingUserDonFailure() {
        when(userToUserDonModelMapper.mapToModel(userToUserDonDTO))
                .thenReturn(userToUserDonation);

        int randomPostID = new Random().nextInt(BOUNDARY);

        when(userToUserDonDTO.getPostID())
                .thenReturn(randomPostID);

        when(userToUserDonDAO.save(userToUserDonation))
                .thenReturn(false);

        when(postService.decrementBags(randomPostID))
                .thenReturn(true);

        Assertions.assertThrows(TransactionException.class,
                () -> transactionService.applyUserDonation(userToUserDonDTO));
    }

    /**
     * tests that an exception is thrown if something wrong happened while decrementing
     * the post required blood bags in the database
     * saving user donation record -> success
     */
    @Test
    public void decrementingFailure() {
        when(userToUserDonModelMapper.mapToModel(userToUserDonDTO))
                .thenReturn(userToUserDonation);

        int randomPostID = new Random().nextInt(BOUNDARY);

        when(userToUserDonDTO.getPostID())
                .thenReturn(randomPostID);

        when(userToUserDonDAO.save(userToUserDonation))
                .thenReturn(true);

        when(postService.decrementBags(randomPostID))
                .thenReturn(false);

        Assertions.assertThrows(TransactionException.class,
                () -> transactionService.applyUserDonation(userToUserDonDTO));
    }

    /**
     * saving successful
     * updating post blood bags successful
     */
    @Test
    public void successfulUserToUserDonation() {
        when(userToUserDonModelMapper.mapToModel(userToUserDonDTO))
                .thenReturn(userToUserDonation);

        int randomPostID = new Random().nextInt(BOUNDARY);
        LocalDate date = LocalDate.now();

        when(userToUserDonDTO.getPostID())
                .thenReturn(randomPostID);

        when(userDAO.updateLastTimeDonatedByNationalID(date, userToUserDonDTO.getDonorNationalID()))
                .thenReturn(1);

        when(userToUserDonDAO.save(userToUserDonation))
                .thenReturn(true);

        when(postService.decrementBags(randomPostID))
                .thenReturn(true);

        Assertions.assertDoesNotThrow(() -> transactionService.applyUserDonation(userToUserDonDTO));
    }


    /**
     * institution blood bags < required bags
     * saving -> don't care since it isn't reached
     */
    @Test
    public void insufficientInstBags() {
        when(instToUserDonDTO.getBagsCount())
                .thenReturn(largeBagsCount);

        when(instDAO.getBagsCount(instToUserDonDTO.getInstitutionEmail(),
                instToUserDonDTO.getBloodType()))
                .thenReturn(smallBagsCount);

        Assertions.assertThrows(InsufficientBloodBags.class,
                () -> transactionService.applyInstitutionDonation(instToUserDonDTO));
    }

    /**
     * institution blood bags == required bags
     * saving successful
     */
    @Test
    public void barelySufficientInstBags() {
        when(instToUserDonModelMapper.mapToModel(instToUserDonDTO))
                .thenReturn(instToUserDonation);

        when(instToUserDonDTO.getBagsCount())
                .thenReturn(smallBagsCount);

        when(instDAO.getBagsCount(instToUserDonDTO.getInstitutionEmail(),
                instToUserDonDTO.getBloodType()))
                .thenReturn(smallBagsCount);

        when(instToUserDonDAO.save(instToUserDonation))
                .thenReturn(true);

        Assertions.assertDoesNotThrow(() -> transactionService.applyInstitutionDonation(instToUserDonDTO));
    }

    /**
     * institution blood bags > required bags
     * saving successful
     */
    @Test
    public void moreThanSufficientInstBags() {
        when(instToUserDonModelMapper.mapToModel(instToUserDonDTO))
                .thenReturn(instToUserDonation);

        when(instToUserDonDTO.getBagsCount())
                .thenReturn(smallBagsCount);

        when(instDAO.getBagsCount(instToUserDonDTO.getInstitutionEmail(),
                instToUserDonDTO.getBloodType()))
                .thenReturn(largeBagsCount);

        when(instToUserDonDAO.save(instToUserDonation))
                .thenReturn(true);

        Assertions.assertDoesNotThrow(() -> transactionService.applyInstitutionDonation(instToUserDonDTO));
    }


    /**
     * tests that an exception is thrown if something wrong happened while saving the record in the database
     * institution blood bags > required bags
     */
    @Test
    public void savingInstDonFailure() {
        when(instToUserDonModelMapper.mapToModel(instToUserDonDTO))
                .thenReturn(instToUserDonation);

        when(instToUserDonDTO.getBagsCount())
                .thenReturn(smallBagsCount);

        when(instDAO.getBagsCount(instToUserDonDTO.getInstitutionEmail(),
                instToUserDonDTO.getBloodType()))
                .thenReturn(largeBagsCount);

        when(instToUserDonDAO.save(instToUserDonation))
                .thenReturn(false);

        Assertions.assertThrows(TransactionException.class,
                () -> transactionService.applyInstitutionDonation(instToUserDonDTO));
    }


}