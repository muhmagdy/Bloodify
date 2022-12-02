package com.bloodify.backend.controller;

import com.bloodify.backend.Utils.JwtUtil;
import com.bloodify.backend.model.UserLoginRequest;
import com.bloodify.backend.model.UserLoginResponse;
import com.bloodify.backend.model.UserLoginResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * LoginController
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/userlogin")
    public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserLoginRequest request) {
        System.out.println("login");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails, new Date(System.currentTimeMillis()));
            return ResponseEntity.ok(new UserLoginResponse(true,
                    "good to go",
                    UserLoginResponseBody.builder().name(request.getUsername())
                            .email(request.getUsername() + "@gmail.com")
                            .token(token)
                            .build()));

        } catch (Exception e) {
            return ResponseEntity.status(401).body(new UserLoginResponse(false, "Wrong credentials", null));
        }


    }

    @GetMapping("/user/hello")
    public String hello(@RequestParam String username) {
        System.out.println(username);
        return "hello ";
    }
}