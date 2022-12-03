package com.bloodify.backend.controller;

import com.bloodify.backend.Utils.JwtUtil;
import com.bloodify.backend.Utils.TokenUtil;
import com.bloodify.backend.model.requests.UserLogInRequest;
import com.bloodify.backend.model.responses.UserLogInResponse;
import com.bloodify.backend.model.responses.UserLoginResponseBody;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


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
    JwtUtil jwtUtil;

    @Autowired
    TokenUtil tokenUtil;


    @PostMapping("/userlogin")
    public ResponseEntity<String> userLogin(Authentication auth) {
        System.out.println("login");
        String token = tokenUtil.generateToken(auth);
        return ResponseEntity.ok(token);

        // try {
        //     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        //     UserDetails userDetails = bloodifyUserDetailsService.loadUserByUsername(request.getEmail());
        //     String token = jwtUtil.generateToken(userDetails, new Date(System.currentTimeMillis()));
        //     return ResponseEntity.ok(new UserLogInResponse(true,
        //             "good to go",
        //             UserLoginResponseBody.builder().name(request.getEmail())
        //                     .email(request.getEmail() + "@gmail.com")
        //                     .token(token)
        //                     .build()));

        // } catch (Exception e) {
        //     log.info("yoooooooooooooooooooooooo");
        //     return ResponseEntity.status(401).body(new UserLogInResponse(false, "Wrong credentials", null));
        // }


    }

    @GetMapping("/user/hello")
    public String hello(@RequestParam String username) {
        System.out.println(username);
        return "hello ";
    }
}