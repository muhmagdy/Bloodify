package com.bloodify.backend.services.interfaces;

import com.bloodify.backend.model.requests.UserLogInRequest;
import com.bloodify.backend.model.requests.UserSignUpRequest;
import com.bloodify.backend.model.responses.UserLogInResponse;
import com.bloodify.backend.model.responses.UserSignUpResponse;

public interface AccountManagerService {
    UserLogInResponse logIn(UserLogInRequest user);

    UserSignUpResponse signUpUser(UserSignUpRequest user);
}
