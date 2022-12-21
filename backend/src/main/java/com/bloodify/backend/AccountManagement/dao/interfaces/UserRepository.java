package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<User> findByBloodTypeIn(List<String> bloodTypes);

    List<User> findByStatus(int status);


    @Transactional
    @Modifying
    @Query(value =
            "UPDATE User " +
            "SET status = :newStatus " +
            "WHERE userID = :userID",
            nativeQuery = true)
    void updateUserStatus(@Param("userID") int userID, @Param("newStatus") int newStatus);

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE User " +
                    "SET longitude = :currentLongitude, latitude = :currentLatitude " +
                    "WHERE userID = :userID",
            nativeQuery = true)
    void updateLongitudeAndLatitude(@Param("userID") int userID,
                                    @Param("currentLongitude") Double currentLongitude,
                                    @Param("currentLatitude") Double currentLatitude);


    @Transactional
    @Modifying
    @Query("update User u set u.lastTimeDonated = ?1 where u.nationalID = ?2")
    int updateLastTimeDonatedByNationalID(@NonNull LocalDate lastTimeDonated,
                                          @NonNull String nationalID);

    @Transactional
    @Modifying
    @Query("update User u set u.lastTimeDonated = ?1, u.bloodType = ?2 where u.nationalID = ?3")
    int updateLastTimeDonatedAndBloodTypeByNationalID(@NonNull LocalDate lastTimeDonated,
                                                      @NonNull String bloodType,
                                                      @NonNull String nationalID);
}
