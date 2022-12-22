package com.bloodify.backend.UserRequests.repository.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // get post by ID
    Post findByPostID(int postID);

    // get specific Post
    Post findPostByUserAndInstitutionAndBloodType(User user, Institution institution, String bloodType);

    // get all posts related to some user as a requester
    List<Post> findPostsByUser(User user);

    // get all posts related to some institution
    List<Post> findPostsByInstitution(Institution institution);

    // get posts by blood type
    @Query()
    List<Post> findByBloodType(String bloodType);

//    @Query("SELECT p.acceptingUsers FROM Post as p WHERE p.user = :user")
//    List<User> findAcceptingUsersByRequester(@Param("user") User user);


    // delete after certain period or those satisfying required bagsNum
//    @Transactional
//    void deletePostsByStartTimeBeforeOrBagsNum(LocalDateTime startTime, int bagsNum);

    @Transactional
    void deletePostsByLastTimeBeforeOrBagsNum(LocalDateTime startTime, int bagsNum);

    // delete specific Post
    @Transactional
    void deletePostByUserAndInstitutionAndBloodType(User user, Institution institution, String bloodType);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Post SET institution_institutionid = :institutionID, blood_type = :bloodType, " +
            "req_bags_number = :req_bags, expiry_at = :expiry_at, created_at = :created_at WHERE postID = :id", nativeQuery = true)
    void updatePostSet(@Param("institutionID") int institutionID, @Param("req_bags") int req_bags,
                       @Param("bloodType") String bloodType, @Param("id") int postID,
                       @Param("created_at") LocalDateTime lastUpdateTime, @Param("expiry_at") LocalDateTime expiryTime);

}
