package com.bloodify.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloodify.backend.Model.UserLoginRequest;
import com.bloodify.backend.Model.UserLoginResponse;

/**
 * LoginController
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class LoginController {

    @PostMapping("/userlogin")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserLoginRequest request){
        return ResponseEntity.ok(new UserLoginResponse());
    }

    @GetMapping("/user/hello")
    public String hello(@RequestParam String username){
        System.out.println(username);
        return "hello ";
    }
}