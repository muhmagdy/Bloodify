package com.bloodify.backend.UserRequests.service;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserDAO userDAO;

    @Autowired
    InstitutionDAO institutionDAO;


    boolean addPost(Post post){
        try{
            this.postRepository.save(post);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    boolean updatePost(Post post){
        Post duplicatedPost = this.postRepository.findPostByUserAndInstitutionAndBloodType(
                post.getUser(), post.getInstitution(), post.getBloodType());
        if (duplicatedPost == null){
            try {
                this.postRepository.save(post);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    Post getSpecificUserPost(Post post){
        return this.postRepository.findPostByUserAndInstitutionAndBloodType(
                post.getUser(), post.getInstitution(), post.getBloodType());
    }

    List<Post> getAllUserPosts(String userEmail){
        User user = this.userDAO.findUserByEmail(userEmail);
        return this.postRepository.findPostsByUser(user);
    }

    List<Post> getAllInstitutionPosts(int institutionID){
        Institution institution = this.institutionDAO.findInstitutionByID(institutionID);
        return this.postRepository.findPostsByInstitution(institution);
    }

    List<Post> getAllBloodTypePosts(String bloodType){
        return this.postRepository.findAllByBloodType(bloodType);
    }

    void deleteUnnecessaryPosts(){
        this.postRepository.deleteAllByStartTimeIsBeforeAndBagsNum(LocalDateTime.now().minusDays(7), 0);
    }

    void deleteSpecificUserPost(Post post){
        this.postRepository.deletePostByUserAndInstitutionAndBloodType(post.getUser(),
                post.getInstitution(), post.getBloodType());
    }
}
