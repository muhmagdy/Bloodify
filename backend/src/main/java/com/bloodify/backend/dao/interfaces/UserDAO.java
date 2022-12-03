package com.bloodify.backend.dao.interfaces;

import com.bloodify.backend.model.entities.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserDAO extends UserDetailsService{
    boolean saveUser(User newUser);

    User findUserByEmail(String email);

    User findUserByNationalID(String nationalID);
    boolean isUsernameAndPasswordMatching(String email, String password);

    List<User> getUsersByBloodType(String bloodType, char bloodSign);
}
