package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.exceptions.DuplicatePostException;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostDaoImp implements PostDao {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserDAO userDAO;

    @Autowired
    InstitutionDAO institutionDAO;

    @Override
    public boolean addPost(Post post){
        Post duplicatedPost = getSpecificUserPost(post);
        try {
            if (duplicatedPost != null) throw new DuplicatePostException();
            this.postRepository.save(post);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePost(Post post){
        this.postRepository.updatePostSet(post.getInstitution().getInstitutionID(), post.getBagsNum(),
                post.getBloodType(), post.getPostID());
        return true;
    }

    @Override
    public String getPostEmail(int iD) {
        Post post = this.getPostByID(iD);
        if (post == null) return null;
        return post.getUser().getEmail();
    }

    @Override
    public Post getPostByID(int id) {
        return this.postRepository.findByPostID(id);
    }

    @Override
    public Post getSpecificUserPost(Post post){
        try {
            return this.postRepository.findPostByUserAndInstitutionAndBloodType(
                    post.getUser(), post.getInstitution(), post.getBloodType());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Post> getUserAllPosts(String userEmail){
        try{
            User user = this.userDAO.findUserByEmail(userEmail);
            return this.postRepository.findPostsByUser(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> getInstitutionAllPosts(int institutionID){
        try {
            Institution institution = this.institutionDAO.findInstitutionByID(institutionID);
            return this.postRepository.findPostsByInstitution(institution);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Post> getAllBloodTypePosts(String bloodType){
        return this.postRepository.findByBloodType(bloodType);
    }

    @Override
    public void deleteUnnecessaryPosts(){
        this.postRepository.deletePostsByStartTimeBeforeOrBagsNum(LocalDateTime.now().minusDays(7), 0);
    }

    @Override
    public boolean deleteSpecificUserPost(Post post){
        try{
            this.postRepository.deletePostByUserAndInstitutionAndBloodType(post.getUser(),
                    post.getInstitution(), post.getBloodType());
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findAcceptingUsersByRequester(User user) {
//        return this.postRepository.findAcceptingUsersByRequester(user);
        // return null;
        return new ArrayList<>();
    }

}
