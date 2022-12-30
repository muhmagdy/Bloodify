package com.bloodify.backend.AccountManagement.dao.classes;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserRepository;
import com.bloodify.backend.AccountManagement.model.authentication.UserAuthentication;
import com.bloodify.backend.AccountManagement.model.entities.User;

import com.bloodify.backend.UserRequests.model.entities.Post;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Required Services:
 * at sign in --> find user with email 'bla', match username with password
 * at sign up --> check that username is not repeated, insert new user (saveUser)
 * get users with blood type 'bla'
 */
@Slf4j
@Service
public class UserDAOImp implements UserDAO {
    @Autowired
    @Qualifier("UserRepository")
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

    @Override
    public User findByID(int userID) {
        Optional<User> user = userRepo.findById(userID);
        return user.orElse(null);
    }

    @Override
    public User findUserByEmailJoin(String email) {
//        List<User> foundUsers = userRepo.findByEmailJoin(email);
//
//        if (foundUsers.isEmpty())
//            return null;
//        else
//            return foundUsers.get(0);
        return null;
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

    public int updateLastTimeDonatedByNationalID(@NonNull LocalDate lastTimeDonated, String nationalID) {
        return this.userRepo.updateLastTimeDonatedByNationalID(lastTimeDonated, nationalID);
    }

    @Override
    public int updateLastTimeDonatedAndBloodTypeByNationalID(LocalDate lastTimeDonated,
                                                             String bloodType,
                                                             String nationalID) {
        return this.userRepo.updateLastTimeDonatedAndBloodTypeByNationalID(lastTimeDonated,
                        bloodType, nationalID);
    }

    public List<User> getUsersByBloodType(String bloodType) {
        return userRepo.findByBloodType(bloodType);
    }

    @Override
    public List<User> getUsersByStatus(int status) {
        return userRepo.findByStatus(status);
    }

    @Override
    public List<User> getUsersByStatusAndDiseases(int status, boolean hasDisease) {
        return userRepo.findByStatusAndHasDiseases(status, hasDisease);
    }

    @Override
    public List<User> getDonorsByPostId(int postID) {
//        return userRepo.findDonorsByPostId(postID);
        return null;
    }

    @Override
    public List<User> findByBloodTypeIn(List<String> bloodTypes) {
        return this.userRepo.findByBloodTypeIn(bloodTypes);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username + " user");
        User user = this.findUserByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username + " not found");
        log.info(user.getEmail());
        // if(!username.equals("foo")) throw new UsernameNotFoundException(username + " not found");
        UserAuthentication userAuth = new UserAuthentication(user);
        return userAuth;
        // return new User("foo", "foo", List.of());
    }

    @Override
    public boolean isUserExistByEmail(String email){
        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        return userRepo.updatePasswordByEmail(newPassword, email) == 1;
    }

}
