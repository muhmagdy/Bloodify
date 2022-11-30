package com.bloodify.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bloodify.backend.Model.UserLoginRequest;
import com.bloodify.backend.Model.UserLoginResponse;

/**
 * LoginController
 */
@Controller
@CrossOrigin
@RequestMapping("/api/v1")
public class LoginController {

    @PostMapping("/userlogin")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserLoginRequest request){
        return ResponseEntity.ok(new UserLoginResponse());
    }
}