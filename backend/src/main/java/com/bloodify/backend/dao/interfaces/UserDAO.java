package com.bloodify.backend.dao.interfaces;

import com.bloodify.backend.model.entities.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserDAO extends UserDetailsService{
    public boolean saveUser(User newUser);

    public User findUserByEmail(String email);

    public User findUserByNationalID(String nationalID);

    public boolean isUsernameAndPasswordMatching(String email, String password);

    public List<User> getUsersByBloodType(String bloodType);
}
