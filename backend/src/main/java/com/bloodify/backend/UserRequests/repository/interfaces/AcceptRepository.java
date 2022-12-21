package com.bloodify.backend.UserRequests.repository.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import com.bloodify.backend.UserRequests.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptRepository extends JpaRepository<AcceptedPost, Integer> {

    List<AcceptedPost> findByPost(Post post);

    List<AcceptedPost> findByUser(User user);
}
