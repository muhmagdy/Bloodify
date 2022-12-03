package com.bloodify.backend.controller;

import com.bloodify.backend.Utils.TokenUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;



/**
 * LoginController
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService bloodifyUserDetailsService;


    @Autowired
    TokenUtil tokenUtil;


    @PostMapping("/userlogin")
    public ResponseEntity<String> userLogin(Authentication auth) {
        log.info("login");
        String token = tokenUtil.generateToken(auth);
        return ResponseEntity.ok(token);

    }

    @GetMapping("/user/hello")
    public String hello(@RequestParam String username) {
        System.out.println(username);
        return "hello ";
    }
}