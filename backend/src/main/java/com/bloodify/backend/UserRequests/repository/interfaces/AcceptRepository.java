package com.bloodify.backend.UserRequests.repository.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import com.bloodify.backend.UserRequests.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AcceptRepository extends JpaRepository<AcceptedPost, Integer> {
    List<AcceptedPost> findByPost(Post post);

    @Transactional
    @Modifying
    @Query("update AcceptedPost a set a.longitude = ?1, a.latitude = ?2, a.threshold = ?3 where a.user = ?4")
    void updateLocationInfo(Double longitude, Double latitude, Double threshold, @NonNull User user);

    List<AcceptedPost> findByUser(User user);

    AcceptedPost findByPostPostIDAndUserUserID(Integer postID, Integer donorID);
}
