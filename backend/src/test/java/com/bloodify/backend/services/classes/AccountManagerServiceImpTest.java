package com.bloodify.backend.services.classes;

import com.bloodify.backend.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.services.exceptions.BothEmailAndNationalIdExists;
import com.bloodify.backend.services.exceptions.EmailExistsException;
import com.bloodify.backend.services.exceptions.NationalIdExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountManagerServiceImpTest {
    @Mock
    UserDAO userDAO;

    @InjectMocks
    AccountManagerServiceImp accountManagerService;

    RandomUserGenerations random = new RandomUserGenerations();

    @Test
    public void testSignUpUserWithExistingEmailOnly() {
        when(userDAO.findUserByNationalID(new User().getNationalID()))
                .thenReturn(null);

        when(userDAO.findUserByEmail(new User().getEmail()))
                .thenReturn(new User());

        Assertions.assertThrows(EmailExistsException.class,
                () -> accountManagerService.userSignUp(new User()));
    }

    @Test
    public void testSignUpUserWithExistingNIdOnly() {
        when(userDAO.findUserByNationalID(new User().getNationalID()))
                .thenReturn(new User());

        when(userDAO.findUserByEmail(new User().getEmail()))
                .thenReturn(null);

        Assertions.assertThrows(NationalIdExistsException.class,
                () -> accountManagerService.userSignUp(new User()));
    }

    @Test
    public void testSignUpUserWithExistingNIdAndEmail() {
        when(userDAO.findUserByNationalID(new User().getNationalID()))
                .thenReturn(new User());

        when(userDAO.findUserByEmail(new User().getEmail()))
                .thenReturn(new User());

        Assertions.assertThrows(BothEmailAndNationalIdExists.class,
                () -> accountManagerService.userSignUp(new User()));
    }

    @Test
    public void testSignUserUpSuccessfully() {
        User user = random.generateRandomUser();
        when(userDAO.findUserByNationalID(user.getNationalID()))
                .thenReturn(null);

        when(userDAO.findUserByEmail(user.getEmail()))
                .thenReturn(null);

        when(userDAO.saveUser(user))
                .thenReturn(true);

        Assertions.assertTrue(accountManagerService.userSignUp(user));
    }
}