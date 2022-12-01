package com.bloodify.backend.dao;

import com.bloodify.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);
    List<User> findByNationalID(String nationalID);
    List<User> findByBloodType(String bloodType, char bloodSign);
//    List<String> getPassword(User user);
}
