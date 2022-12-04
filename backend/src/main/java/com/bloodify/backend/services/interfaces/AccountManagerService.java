package com.bloodify.backend.services.interfaces;

import com.bloodify.backend.model.responses.UserLoginResponseBody;
import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.model.requests.UserLogInRequest;

public interface AccountManagerService {
    UserLoginResponseBody logIn(UserLogInRequest user);


    boolean signUpUser(User user);

    boolean signUpInstitution(Institution institution);
}
