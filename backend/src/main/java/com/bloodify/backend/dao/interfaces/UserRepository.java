package com.bloodify.backend.dao.interfaces;

import com.bloodify.backend.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);

    List<User> findByNationalID(String nationalID);

    List<User> findByBloodType(String bloodType);
}
