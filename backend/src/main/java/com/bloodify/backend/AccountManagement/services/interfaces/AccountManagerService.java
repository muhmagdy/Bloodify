package com.bloodify.backend.AccountManagement.services.interfaces;

import com.bloodify.backend.AccountManagement.model.responses.LoginResponseBody;

import org.springframework.security.core.Authentication;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
// import com.bloodify.backend.model.requests.UserLogInRequest;

public interface AccountManagerService {
    LoginResponseBody userLogIn(Authentication auth);
    LoginResponseBody instLogIn(Authentication auth);

    boolean userSignUp(User user);
    boolean instSignUp(Institution institution);

    void userSignOut(Authentication auth);
    void instSignOut(Authentication auth);
}
