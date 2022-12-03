package com.bloodify.backend.Services.interfaces;

import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.services.classes.AccountManagerServiceImp;
import com.bloodify.backend.services.exceptions.BothEmailAndNationalIdExists;
import com.bloodify.backend.services.exceptions.EmailExistsException;
import com.bloodify.backend.services.exceptions.NationalIdExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountManagerServiceTest {
    @Mock
    UserDAO userDAO;

    @InjectMocks
    AccountManagerServiceImp accountManagerService;

    @Test
    public void testSignUpUserWithExistingEmailOnly() {
        when(userDAO.findUserByNationalID(new User().getNationalID()))
                .thenReturn(null);

        when(userDAO.findUserByEmail(new User().getEmail()))
                .thenReturn(new User());

        Assertions.assertThrows(EmailExistsException.class,
                () -> accountManagerService.signUpUser(new User()));
    }

    @Test
    public void testSignUpUserWithExistingNIdOnly() {
        when(userDAO.findUserByNationalID(new User().getNationalID()))
                .thenReturn(new User());

        when(userDAO.findUserByEmail(new User().getEmail()))
                .thenReturn(null);

        Assertions.assertThrows(NationalIdExistsException.class,
                () -> accountManagerService.signUpUser(new User()));
    }

    @Test
    public void testSignUpUserWithExistingNIdAndEmail() {
        when(userDAO.findUserByNationalID(new User().getNationalID()))
                .thenReturn(new User());

        when(userDAO.findUserByEmail(new User().getEmail()))
                .thenReturn(new User());

        Assertions.assertThrows(BothEmailAndNationalIdExists.class,
                () -> accountManagerService.signUpUser(new User()));
    }

    @Test
    public void testSignUserUpSuccessfully() {
        when(userDAO.findUserByNationalID(new User().getNationalID()))
                .thenReturn(null);

        when(userDAO.findUserByEmail(new User().getNationalID()))
                .thenReturn(null);

        when(userDAO.saveUser(new User()))
                .thenReturn(true);

        Assertions.assertTrue(accountManagerService.signUpUser(new User()));
    }


}