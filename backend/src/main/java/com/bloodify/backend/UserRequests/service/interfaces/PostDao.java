package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.UserRequests.model.entities.Post;

import java.util.List;

public interface PostDao {
    boolean addPost(Post post);  //done

    Post getSpecificUserPost(Post post);  // done locally during transactions

    List<Post> getUserAllPosts(String userEmail); // done

    List<Post> getInstitutionAllPosts(int institutionID); /// may be needed in next phase

    List<Post> getAllBloodTypePosts(String bloodType); // may not be needed

    void deleteUnnecessaryPosts();   //// done locally during transactions

    boolean deleteSpecificUserPost(Post post);   // done

    boolean updatePost(Post post);   // done
}
