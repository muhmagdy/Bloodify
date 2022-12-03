package com.bloodify.backend.services.classes;

import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.model.UserLoginResponseBody;
import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.model.requests.UserLogInRequest;
import com.bloodify.backend.model.responses.UserLogInResponse;
import com.bloodify.backend.services.interfaces.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


class EmailExistsException extends RuntimeException {
}

class NationalIDExistsException extends RuntimeException {
}

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
        if (userDAO.findUserByNationalID(user.getNationalID()) == null) {
            throw new EmailExistsException();
        } else if (!userDAO.saveUser(user)) {
            throw new NationalIDExistsException();
        }
        return true;
    }

    @Override
    public boolean signUpInstitution(Institution institution) throws Exception {
        return false;
    }
}
