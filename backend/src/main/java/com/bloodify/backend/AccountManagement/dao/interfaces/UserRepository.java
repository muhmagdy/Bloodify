package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);

    List<User> findByNationalID(String nationalID);

    List<User> findByBloodType(String bloodType);

    @Transactional
    @Modifying
    @Query("update User u set u.lastTimeDonated = ?1 where u.nationalID = ?2")
    int updateLastTimeDonatedByNationalID(@NonNull LocalDate lastTimeDonated, String nationalID);
}
