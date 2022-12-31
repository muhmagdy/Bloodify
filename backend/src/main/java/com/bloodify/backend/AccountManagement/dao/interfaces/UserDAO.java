package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;

import java.time.LocalDate;
import java.util.List;

import com.bloodify.backend.UserRequests.model.entities.Post;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserDAO extends UserDetailsService{
    public boolean saveUser(User newUser);

    public User findUserByEmail(String email);

    User findByID(int userID);

    public User findUserByEmailJoin(String email);

    public User findUserByNationalID(String nationalID);

    public boolean isUsernameAndPasswordMatching(String email, String password);

    public List<User> getUsersByBloodType(String bloodType);

    public List<User> getUsersByStatus(int status);

    public List<User> getUsersByStatusAndDiseases(int status, boolean hasDisease);

    public List<User> getDonorsByPostId(int postID);

    public List<User> findByBloodTypeIn(List<String> bloodTypes);

    int updateLastTimeDonatedByNationalID(LocalDate lastTimeDonated, String nationalID);

    int updateLastTimeDonatedAndBloodTypeByNationalID(LocalDate lastTimeDonated,
                                                      String bloodType,
                                                      String nationalID);

    int updateHasDiseases(boolean hasDiseases, String email);
    
    public boolean isUserExistByEmail(String email);

    public boolean updatePassword(String email, String newPassword);

}
