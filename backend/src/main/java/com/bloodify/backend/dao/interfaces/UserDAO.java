package com.bloodify.backend.dao.interfaces;

import com.bloodify.backend.model.entities.User;

import java.util.List;

public interface UserDAO {
    boolean saveUser(User newUser);

    User findUserByEmail(String email);

    User findUserByNationalID(String nationalID);
    boolean isUsernameAndPasswordMatching(String email, String password);

    List<User> getUsersByBloodType(String bloodType, char bloodSign);
}
