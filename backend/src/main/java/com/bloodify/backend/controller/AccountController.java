package com.bloodify.backend.controller;

import com.bloodify.backend.exception.SignupException;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.model.responses.UserSignUpResponse;
import com.bloodify.backend.services.interfaces.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    AccountManagerService accountManagerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserSignUpResponse signUpUser(@RequestBody User user) throws Exception {
        System.out.println(user.toString());
        boolean isCreated = accountManagerService.signUpUser(user);
        if(isCreated)
            return new UserSignUpResponse(true, "Success");
        else
            return new UserSignUpResponse(false, "Error occurred while signing up.");
    }

    @PostMapping("/user/auth")
    public Object signInUser(@RequestBody Object credentials){
        // Check the password with db, it correct assign new token
        // and return it.
        // Returns Status Code 200 OK                   if signed in successfully
        // Returns Status Code 422 UNPROCESSABLE ENTITY if email/password incorrect
        return null;
    }

    @PutMapping("/user/password")
    public Object resetPasswordUser(@RequestBody Object email){
        // Sends an email with reset password link
        // Always returns 200 OK (even if email doesn't exist)
        return null;
    }

    @PostMapping("/institution")
    public Object signUpInstitution(@RequestBody Object user){
        // Here It will try to sign up the user then send an email to confirm
        // Returns Status Code 201 CREATED if created successfully
        // Returns Status Code 200 OK      if cannot be created, reason here : https://stackoverflow.com/a/53144807
        return null;
    }

    @PostMapping("/institution/auth")
    public Object signInInstitution(@RequestBody Object credentials){
        // Check the password with db, it correct assign new token
        // and return it.
        // Returns Status Code 200 OK                   if signed in successfully
        // Returns Status Code 422 UNPROCESSABLE ENTITY if email/password incorrect
        return null;
    }

    @PutMapping("/institution/password")
    public Object resetPasswordInstitution(@RequestBody Object email){
        // Sends an email with reset password link
        // Always returns 200 OK (even if email doesn't exist)
        return null;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SignupException.class)
    public UserSignUpResponse handleSignupException(SignupException exception){
        return new UserSignUpResponse(false, exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public UserSignUpResponse handleIncorrectFormatException(){
        return new UserSignUpResponse(false, "Incorrect request format");
    }
}
