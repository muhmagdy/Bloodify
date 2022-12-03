package com.bloodify.backend.services.classes;

import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.model.requests.UserLogInRequest;
import com.bloodify.backend.model.requests.UserSignUpRequest;
import com.bloodify.backend.model.responses.UserLogInResponse;
import com.bloodify.backend.model.responses.UserSignUpResponse;
import com.bloodify.backend.services.interfaces.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerServiceImp implements AccountManagerService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserLogInResponse logIn(UserLogInRequest user) {
        return null;
    }

    @Override
    public UserSignUpResponse signUpUser(UserSignUpRequest user) {
        return null;
    }
}
