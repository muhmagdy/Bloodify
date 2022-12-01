package com.bloodify.backend.service;

import com.bloodify.backend.dao.UserRepository;
import com.bloodify.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Required Services:
 * at sign in --> find user with email 'bla', match username with password
 * at sign up --> check that username is not repeated, insert new user (saveUser)
 * get users with blood type 'bla'
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    public boolean saveUser (User newUser) {
        List<User> foundUsers = userRepo.findByEmail(newUser.getEmail());
        if(foundUsers.isEmpty()) {
            userRepo.save(newUser);
            return true;
        }
        return false;
    }

    public User findUserByEmail(String email) {
        List<User> foundUsers = userRepo.findByEmail(email);

        if(foundUsers.isEmpty())
            return null;
        else if(foundUsers.size() == 1)
            return foundUsers.get(0);
        else {
            System.out.println("Database is inconsistent");
            return null;
        }
    }

    public boolean isUsernameAndPasswordMatching(String email, String password) {
        User signingIn = findUserByEmail(email);
        if(signingIn!=null){
            String currentPassword = signingIn.getPassword();
            return currentPassword.equals(password);
        }
        return false;
    }

    public List<User> getUsersByBloodType (String bloodType, char bloodSign) {
        return userRepo.findByBloodType(bloodType, bloodSign);
    }

}
