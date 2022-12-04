package com.bloodify.backend.dao.classes;

import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.dao.interfaces.UserRepository;
import com.bloodify.backend.model.entities.User;
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
public class UserDAOImp implements UserDAO {
    @Autowired
    UserRepository userRepo;


    public boolean saveUser(User newUser) {
        try {
            userRepo.save(newUser);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public User findUserByEmail(String email) {
        List<User> foundUsers = userRepo.findByEmail(email);

        if (foundUsers.isEmpty())
            return null;
        else
            return foundUsers.get(0);
    }

    public User findUserByNationalID(String nationalID) {
        List<User> foundUsers = userRepo.findByNationalID(nationalID);

        if (foundUsers.isEmpty())
            return null;
        else
            return foundUsers.get(0);
    }

    public boolean isUsernameAndPasswordMatching(String email, String password) {
        User signingIn = findUserByEmail(email);
        if (signingIn != null) {
            String currentPassword = signingIn.getPassword();
            return currentPassword.equals(password);
        }
        return false;
    }

    public List<User> getUsersByBloodType(String bloodType) {
        return userRepo.findByBloodType(bloodType);
    }


}
