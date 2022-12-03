package com.bloodify.backend.dao.interfaces;

import com.bloodify.backend.model.entities.User;

import java.util.List;

public interface UserDAO {
    public boolean saveUser(User newUser);

    public User findUserByEmail(String email);

    public User findUserByNationalID(String nationalID);

    public boolean isUsernameAndPasswordMatching(String email, String password);

    public List<User> getUsersByBloodType(String bloodType);
}
