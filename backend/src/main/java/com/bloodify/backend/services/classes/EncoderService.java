package com.bloodify.backend.services.classes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
