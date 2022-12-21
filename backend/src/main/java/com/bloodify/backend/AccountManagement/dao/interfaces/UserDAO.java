package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;

import java.util.List;

import com.bloodify.backend.UserRequests.model.entities.Post;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserDAO extends UserDetailsService{
    public boolean saveUser(User newUser);

    public User findUserByEmail(String email);

    public User findUserByEmailJoin(String email);

    public User findUserByNationalID(String nationalID);

    public boolean isUsernameAndPasswordMatching(String email, String password);

    public List<User> getUsersByBloodType(String bloodType);

    public List<User> getUsersByStatus(int status);

    public List<User> getUsersByStatusAndDiseases(int status, boolean hasDisease);

    public List<User> getDonorsByPostId(int postID);

    public List<User> findByBloodTypeIn(List<String> bloodTypes);

    public void updateStatus(int userID, int userStatus);

    public void updateLongitudeAndLatitude(int userID, Double longitude, Double latitude);

    public Post findAcceptedPostByAcceptor (User user);
}
