package com.bloodify.backend.services.classes;

import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.exception.EmailExistsException;
import com.bloodify.backend.exception.NationalIdExistsException;
import com.bloodify.backend.model.UserLoginResponseBody;
import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.model.requests.UserLogInRequest;
import com.bloodify.backend.services.exceptions.BothEmailAndNationalIdExists;
import com.bloodify.backend.services.exceptions.EmailExistsException;
import com.bloodify.backend.services.exceptions.NationalIdExistsException;
import com.bloodify.backend.services.interfaces.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerServiceImp implements AccountManagerService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserLoginResponseBody logIn(UserLogInRequest user) {
        return null;
    }

    @Override
    public boolean signUpUser(User user) {
        boolean nationalIdExists = userDAO.findUserByNationalID(user.getNationalID()) != null;
        boolean emailExists = userDAO.findUserByEmail(user.getEmail()) != null;

        if (nationalIdExists) {
            if (emailExists)
                throw new BothEmailAndNationalIdExists();

            throw new NationalIdExistsException();
        } else if (emailExists)
            throw new EmailExistsException();

        return userDAO.saveUser(user);
    }

    @Override
    public boolean signUpInstitution(Institution institution) {
        return false;
    }
}
