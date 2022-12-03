package com.bloodify.backend.services.classes;

import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.exception.EmailExistsException;
import com.bloodify.backend.exception.NationalIdExistsException;
import com.bloodify.backend.model.UserLoginResponseBody;
import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.model.requests.UserLogInRequest;
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
    public boolean signUpUser(User user) throws Exception {
        if (userDAO.findUserByNationalID(user.getNationalID()) != null) {
            throw new NationalIdExistsException();
        } else if (!userDAO.saveUser(user)) {
            throw new EmailExistsException();
        }
        return true;
    }

    @Override
    public boolean signUpInstitution(Institution institution) throws Exception {
        return false;
    }
}
