package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (duplicatedPost != null) return false;
        try{
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
        post = getSpecificUserPost(post);
        if(post == null) return false;
        this.postRepository.updatePostSet(post.getInstitution().getInstitutionID(), post.getBagsNum(),
                post.getBloodType(), post.getPostID());
        return true;
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
        Institution institution = this.institutionDAO.findInstitutionByID(institutionID);
        return this.postRepository.findPostsByInstitution(institution);
    }

    @Override
    public List<Post> getAllBloodTypePosts(String bloodType){
        return this.postRepository.findAllByBloodType(bloodType);
    }

    @Override
    public void deleteUnnecessaryPosts(){
        this.postRepository.deleteAllByStartTimeIsBeforeAndBagsNum(LocalDateTime.now().minusDays(7), 0);
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

}
