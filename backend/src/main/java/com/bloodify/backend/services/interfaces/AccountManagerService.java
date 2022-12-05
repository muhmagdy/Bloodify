package com.bloodify.backend.services.interfaces;

import com.bloodify.backend.model.responses.UserLoginResponseBody;

import org.springframework.security.core.Authentication;

import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;
// import com.bloodify.backend.model.requests.UserLogInRequest;

public interface AccountManagerService {
    UserLoginResponseBody userlogIn(Authentication auth);
    UserLoginResponseBody instlogIn(Authentication auth);



    boolean signUpUser(User user);

    boolean signUpInstitution(Institution institution);
}
