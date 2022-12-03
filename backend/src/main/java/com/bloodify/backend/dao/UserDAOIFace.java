package com.bloodify.backend.dao;

import com.bloodify.backend.model.User;
import java.util.List;

public interface UserDAOIFace {
    public boolean saveUser (User newUser);
    public User findUserByEmail(String email);
    public User findUserByNationalID(String nationalID);
    public boolean isUsernameAndPasswordMatching(String email, String password);
    public List<User> getUsersByBloodType (String bloodType);
}
