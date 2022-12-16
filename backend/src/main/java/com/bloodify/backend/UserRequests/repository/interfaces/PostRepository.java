package com.bloodify.backend.UserRequests.repository.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


    // get specific Post
    Post findPostByUserAndInstitutionAndBloodType(User user, Institution institution, String bloodType);

    // get all user posts
    List<Post> findPostsByUser(User user);

    // get all posts related to some institution
    List<Post> findPostsByInstitution(Institution institution);

    // get posts by blood type
    List<Post> findAllByBloodType(String bloodType);


    // delete after certain period or those satisfying required bagsNum
    void deleteAllByStartTimeIsBeforeAndBagsNum(LocalDateTime startTime, int bagsNum);


    // delete specific Post
    void deletePostByUserAndInstitutionAndBloodType(User user, Institution institution, String bloodType);

    @Query(value = "UPDATE Post SET institution_institutionid = :institutionID, blood_type = :bloodType, req_bags_number = :req_bags WHERE postID = :id",
    nativeQuery = true)
    void updatePostSet(@Param("institutionID") int institutionID, @Param("req_bags") int req_bags,
                       @Param("bloodType") String bloodType, @Param("id") int postID);
}
