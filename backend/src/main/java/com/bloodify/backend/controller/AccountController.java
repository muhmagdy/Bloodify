package com.bloodify.backend.controller;

import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.services.exceptions.SignupDuplicateException;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.model.responses.SignUpResponse;
import com.bloodify.backend.model.responses.UserLogInResponse;
import com.bloodify.backend.model.responses.UserLoginResponseBody;
import com.bloodify.backend.services.interfaces.AccountManagerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class AccountController {

    private static final String test = "";

    @Autowired
    AccountManagerService accountManagerService;


    @PostMapping(test + "/user")
    public ResponseEntity<SignUpResponse> signUpUser(@RequestBody User user) throws Exception {
        System.out.println(user.toString());
        boolean isCreated = accountManagerService.signUpUser(user);
        if(isCreated)
            return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponse(true, "Success"));
        else
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new SignUpResponse(false, "Error occurred while signing up."));
    }

    @PostMapping(test + "/user/auth")
    public Object signInUser(Authentication credentials){
        // Check the password with db, it correct assign new token
        // and return it.
        // Returns Status Code 200 OK                   if signed in successfully
        // Returns Status Code 422 UNPROCESSABLE ENTITY if email/password incorrect
        UserLoginResponseBody body = accountManagerService.userlogIn(credentials);
        if(body == null){
            return ResponseEntity.status(422).body(new UserLogInResponse(false, "wrong credentials", body));
        }
        return ResponseEntity.ok(new UserLogInResponse(true, "login successful", body));

    }

    @PutMapping(test + "/user/password")
    public Object resetPasswordUser(@RequestBody Object email){
        // Sends an email with reset password link
        // Always returns 200 OK (even if email doesn't exist)
        return null;
    }

    @PostMapping(test + "/institution")
    public Object signUpInstitution(@RequestBody Institution institution){
        System.out.println(institution.toString());
        boolean isCreated = accountManagerService.signUpInstitution(institution);
        if(isCreated)
            return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponse(true, "Success"));
        else
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new SignUpResponse(false, "Error occurred while signing up."));
    }

    @PostMapping(test + "/institution/auth")
    public Object signInInstitution(Authentication credentials){
        // Check the password with db, it correct assign new token
        // and return it.
        // Returns Status Code 200 OK                   if signed in successfully
        // Returns Status Code 422 UNPROCESSABLE ENTITY if email/password incorrect
        UserLoginResponseBody body = accountManagerService.instlogIn(credentials);
        if(body == null){
            return ResponseEntity.status(422).body(new UserLogInResponse(false, "wrong credentials", body));
        }
        return ResponseEntity.ok(new UserLogInResponse(true, "login successful", body));
    }

    @PutMapping(test + "/institution/password")
    public Object resetPasswordInstitution(@RequestBody Object email){
        // Sends an email with reset password link
        // Always returns 200 OK (even if email doesn't exist)
        return null;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SignupDuplicateException.class)
    public SignUpResponse handleSignupException(SignupDuplicateException exception){
        return new SignUpResponse(false, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public SignUpResponse handleIncorrectFormatException(){
        return new SignUpResponse(false, "Incorrect format");
    }
}
