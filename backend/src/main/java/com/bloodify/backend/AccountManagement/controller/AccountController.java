package com.bloodify.backend.AccountManagement.controller;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.services.exceptions.SignupDuplicateException;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.AccountManagement.model.responses.SignUpResponse;
import com.bloodify.backend.AccountManagement.model.responses.LogInResponse;
import com.bloodify.backend.AccountManagement.model.responses.LoginResponseBody;
import com.bloodify.backend.AccountManagement.services.interfaces.AccountManagerService;

import com.bloodify.backend.UserRequests.model.request.PasswordChangeRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
        boolean isCreated = accountManagerService.userSignUp(user);
        if(isCreated)
            return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponse(true, "Success"));
        else
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new SignUpResponse(false, "Error occurred while signing up."));
    }

    // Check the password with db, it correct assign new token
    // and return it.
    // Returns Status Code 200 OK                   if signed in successfully
    // Returns Status Code 422 UNPROCESSABLE ENTITY if email/password incorrect
    @PostMapping(test + "/user/auth")
    public ResponseEntity<LogInResponse> signInUser(Authentication credentials){
        LoginResponseBody body = accountManagerService.userLogIn(credentials);
        if(body == null){
            return ResponseEntity.status(422).body(new LogInResponse(false, "wrong credentials", body));
        }
        return ResponseEntity.ok(new LogInResponse(true, "login successful", body));
    }

    @PostMapping(test + "/institution")
    public ResponseEntity<SignUpResponse> signUpInstitution(@RequestBody Institution institution){
        System.out.println(institution.toString());
        boolean isCreated = accountManagerService.instSignUp(institution);
        if(isCreated)
            return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponse(true, "Success"));
        else
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new SignUpResponse(false, "Error occurred while signing up."));
    }

    // Check the password with db, it correct assign new token
    // and return it.
    // Returns Status Code 200 OK                   if signed in successfully
    // Returns Status Code 422 UNPROCESSABLE ENTITY if email/password incorrect
    @PostMapping(test + "/institution/auth")
    public ResponseEntity<LogInResponse> signInInstitution(Authentication credentials){
        LoginResponseBody body = accountManagerService.instLogIn(credentials);
        if(body == null){
            return ResponseEntity.status(422).body(new LogInResponse(false, "wrong credentials", body));
        }
        return ResponseEntity.ok(new LogInResponse(true, "login successful", body));
    }

    @PostMapping(test + "/password")
    public ResponseEntity<SignUpResponse> requestPasswordChange (@RequestParam String email){
        return ResponseEntity.status(200).body(accountManagerService.sendVerificationCode(email));
    }

    @PatchMapping(test + "/password")
    public ResponseEntity<SignUpResponse> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest){
        if(accountManagerService.resetPassword(
                passwordChangeRequest.getEmail(), passwordChangeRequest.getCode(), passwordChangeRequest.getNewPassword()
        )) {
            return ResponseEntity.status(200).body(new SignUpResponse(
                    true, "Password changed successfully"
            ));
        }
        return ResponseEntity.status(200).body(new SignUpResponse(
                false, "Incorrect code, please try again."
        ));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SignupDuplicateException.class)
    public SignUpResponse handleSignupException(SignupDuplicateException exception){
        return new SignUpResponse(false, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class})
    public SignUpResponse handleIncorrectFormatException(){
        return new SignUpResponse(false, "Incorrect format");
    }
}
