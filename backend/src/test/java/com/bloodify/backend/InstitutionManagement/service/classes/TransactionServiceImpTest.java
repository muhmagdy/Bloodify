package com.bloodify.backend.InstitutionManagement.service.classes;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.InstitutionManagement.dto.InstitutionDonationDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserDonationDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InsufficientBloodBags;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.TransactionException;
import com.bloodify.backend.InstitutionManagement.model.InstitutionDonation;
import com.bloodify.backend.InstitutionManagement.model.UserDonation;
import com.bloodify.backend.InstitutionManagement.model.mapper.InstitutionDonationModelMapper;
import com.bloodify.backend.InstitutionManagement.model.mapper.UserDonationModelMapper;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstitutionDonationDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserDonationDAO;
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
    UserDonationDAO userDonDAO;

    @Mock
    UserDonationDTO userDonDTO;

    @Mock
    UserDonationModelMapper userDonModelMapper;

    @Mock
    UserDonation userDonModel;

    @Mock
    PostService postService;

    @Mock
    InstitutionDAO instDAO;

    @Mock
    InstitutionDonationDAO instDonDAO;

    @Mock
    InstitutionDonationDTO instDonDTO;

    @Mock
    InstitutionDonationModelMapper instDonModelMapper;

    @Mock
    InstitutionDonation instDonModel;

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
        when(userDonModelMapper.mapToModel(userDonDTO))
                .thenReturn(userDonModel);

        int randomPostID = new Random().nextInt(BOUNDARY);

        when(userDonDTO.getPostID())
                .thenReturn(randomPostID);

        when(userDonDAO.save(userDonModel))
                .thenReturn(false);

        when(postService.decrementBags(randomPostID))
                .thenReturn(true);

        Assertions.assertThrows(TransactionException.class,
                () -> transactionService.applyUserDonation(userDonDTO));
    }

    /**
     * tests that an exception is thrown if something wrong happened while decrementing
     * the post required blood bags in the database
     * saving user donation record -> success
     */
    @Test
    public void decrementingFailure() {
        when(userDonModelMapper.mapToModel(userDonDTO))
                .thenReturn(userDonModel);

        int randomPostID = new Random().nextInt(BOUNDARY);

        when(userDonDTO.getPostID())
                .thenReturn(randomPostID);

        when(userDonDAO.save(userDonModel))
                .thenReturn(true);

        when(postService.decrementBags(randomPostID))
                .thenReturn(false);

        Assertions.assertThrows(TransactionException.class,
                () -> transactionService.applyUserDonation(userDonDTO));
    }

    /**
     * saving successful
     * updating post blood bags successful
     * donor NID was found therefore an update happen
     */
    @Test
    public void successfulUserToUserDonationWithUpdating() {
        when(userDonModelMapper.mapToModel(userDonDTO))
                .thenReturn(userDonModel);

        int randomPostID = new Random().nextInt(BOUNDARY);
        LocalDate date = LocalDate.now();

        when(userDonDTO.getPostID())
                .thenReturn(randomPostID);

        when(userDAO.updateLastTimeDonatedByNationalID(date, userDonDTO.getDonorNationalID()))
                .thenReturn(1);

        when(userDonDAO.save(userDonModel))
                .thenReturn(true);

        when(postService.decrementBags(randomPostID))
                .thenReturn(true);

        Assertions.assertTrue(() -> transactionService.applyUserDonation(userDonDTO));
    }

    /**
     * saving successful
     * updating post blood bags successful
     * donor NID was found therefore no update happen
     */
    @Test
    public void successfulUserToUserDonationWithoutUpdating() {
        when(userDonModelMapper.mapToModel(userDonDTO))
                .thenReturn(userDonModel);

        int randomPostID = new Random().nextInt(BOUNDARY);
        LocalDate date = LocalDate.now();

        when(userDonDTO.getPostID())
                .thenReturn(randomPostID);

        when(userDAO.updateLastTimeDonatedByNationalID(date, userDonDTO.getDonorNationalID()))
                .thenReturn(0);

        when(userDonDAO.save(userDonModel))
                .thenReturn(true);

        when(postService.decrementBags(randomPostID))
                .thenReturn(true);

        Assertions.assertFalse(() -> transactionService.applyUserDonation(userDonDTO));
    }

    /**
     * institution blood bags < required bags
     * saving -> don't care since it isn't reached
     */
    @Test
    public void insufficientInstBags() {
        when(instDonDTO.getBagsCount())
                .thenReturn(largeBagsCount);

        when(instDAO.getBagsCount(instDonDTO.getInstitutionEmail(),
                instDonDTO.getBloodType()))
                .thenReturn(smallBagsCount);

        Assertions.assertThrows(InsufficientBloodBags.class,
                () -> transactionService.applyInstitutionDonation(instDonDTO));
    }

    /**
     * institution blood bags == required bags
     * saving successful
     */
    @Test
    public void barelySufficientInstBags() {
        when(instDonModelMapper.mapToModel(instDonDTO))
                .thenReturn(instDonModel);

        when(instDonDTO.getBagsCount())
                .thenReturn(smallBagsCount);

        when(instDAO.getBagsCount(instDonDTO.getInstitutionEmail(),
                instDonDTO.getBloodType()))
                .thenReturn(smallBagsCount);

        when(instDonDAO.save(instDonModel))
                .thenReturn(true);

        Assertions.assertDoesNotThrow(() -> transactionService.applyInstitutionDonation(instDonDTO));
    }

    /**
     * institution blood bags > required bags
     * saving successful
     */
    @Test
    public void moreThanSufficientInstBags() {
        when(instDonModelMapper.mapToModel(instDonDTO))
                .thenReturn(instDonModel);

        when(instDonDTO.getBagsCount())
                .thenReturn(smallBagsCount);

        when(instDAO.getBagsCount(instDonDTO.getInstitutionEmail(),
                instDonDTO.getBloodType()))
                .thenReturn(largeBagsCount);

        when(instDonDAO.save(instDonModel))
                .thenReturn(true);

        Assertions.assertDoesNotThrow(() -> transactionService.applyInstitutionDonation(instDonDTO));
    }


    /**
     * tests that an exception is thrown if something wrong happened while saving the record in the database
     * institution blood bags > required bags
     */
    @Test
    public void savingInstDonFailure() {
        when(instDonModelMapper.mapToModel(instDonDTO))
                .thenReturn(instDonModel);

        when(instDonDTO.getBagsCount())
                .thenReturn(smallBagsCount);

        when(instDAO.getBagsCount(instDonDTO.getInstitutionEmail(),
                instDonDTO.getBloodType()))
                .thenReturn(largeBagsCount);

        when(instDonDAO.save(instDonModel))
                .thenReturn(false);

        Assertions.assertThrows(TransactionException.class,
                () -> transactionService.applyInstitutionDonation(instDonDTO));
    }


}