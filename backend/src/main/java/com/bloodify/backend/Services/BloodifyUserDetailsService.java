package com.bloodify.backend.Services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BloodifyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("foo")) throw new UsernameNotFoundException(username + " not found");
        return new User("foo", "foo", new ArrayList<>());
    }
    
}
