package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Transactional
//    @Query("SELECT u FROM user u WHERE email = :email")
    List<User> findByEmail(String email);

//    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.posts WHERE email = :email")
//    List<User> findByEmailJoin(@Param("email") String email);

    List<User> findByNationalID(String nationalID);

    List<User> findByBloodType(String bloodType);

    List<User> findByBloodTypeIn(List<String> bloodTypes);

    List<User> findByStatus(int status);

    List<User> findByStatusAndHasDiseases(int status, boolean hasDisease);

//    @Query("SELECT u.acceptedPost FROM User u where u.email = :email")
//    Post findAcceptedPostByAcceptorEmail(@Param("email") String acceptorEmail);
//
//    @Query("SELECT u FROM User u WHERE u.acceptedPost.postID = :post_id")
//    List<User> findDonorsByPostId(@Param("post_id") int post_id);




    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update User u set u.lastTimeDonated = ?1 where u.nationalID = ?2")
    int updateLastTimeDonatedByNationalID(@NonNull LocalDate lastTimeDonated,
                                          @NonNull String nationalID);

    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update User u set u.lastTimeDonated = ?1, u.bloodType = ?2 where u.nationalID = ?3")
    int updateLastTimeDonatedAndBloodTypeByNationalID(@NonNull LocalDate lastTimeDonated,
                                                      @NonNull String bloodType,
                                                      @NonNull String nationalID);
}
