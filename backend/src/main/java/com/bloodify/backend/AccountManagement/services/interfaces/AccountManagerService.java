package com.bloodify.backend.AccountManagement.services.interfaces;

import com.bloodify.backend.AccountManagement.model.responses.LoginResponseBody;

import com.bloodify.backend.AccountManagement.model.responses.SignUpResponse;
import org.springframework.security.core.Authentication;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
// import com.bloodify.backend.model.requests.UserLogInRequest;

public interface AccountManagerService {
    LoginResponseBody userLogIn(Authentication auth, String mobileToken);

    LoginResponseBody instLogIn(Authentication auth);

    boolean userSignUp(User user);

    boolean instSignUp(Institution institution);

    boolean userSignOut(Authentication auth);

    void instSignOut(Authentication auth);

    boolean updateHasDiseases(boolean hasDiseases, String email);

    SignUpResponse sendVerificationCode(String email);
    
    boolean resetPassword(String email, String code, String newPassword);
}
