package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;

import java.util.List;

public interface PostDao {
    boolean addPost(Post post);  //done

    Post getSpecificUserPost(String userEmail, int institutionId, String bloodType);  // done locally during transactions

    Post getSpecificUserPost(Post post);
    List<Post> getUserAllPosts(String userEmail); // done

    List<Post> getInstitutionAllPosts(int institutionID); /// may be needed in next phase

    List<Post> getInstitutionAllPosts(String institutionEmail);

    List<Post> getAllBloodTypePosts(String bloodType); // may not be needed

    void deleteUnnecessaryPosts();   //// done locally during transactions

    boolean deleteSpecificUserPost(Post post);   // done

    boolean updatePost(Post post);   // done

    String getPostEmail(int iD);

    Post getPostByID(int id);

    List<User> findAcceptingUsersByRequester(User user);
}
