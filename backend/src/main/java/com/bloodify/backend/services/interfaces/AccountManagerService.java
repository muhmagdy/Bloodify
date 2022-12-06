package com.bloodify.backend.services.interfaces;

import com.bloodify.backend.model.responses.LoginResponseBody;

import org.springframework.security.core.Authentication;

import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;
// import com.bloodify.backend.model.requests.UserLogInRequest;

public interface AccountManagerService {
    LoginResponseBody userLogIn(Authentication auth);
    LoginResponseBody instLogIn(Authentication auth);

    boolean userSignUp(User user);
    boolean instSignUp(Institution institution);

    void userSignOut(Authentication auth);
    void instSignOut(Authentication auth);
}
