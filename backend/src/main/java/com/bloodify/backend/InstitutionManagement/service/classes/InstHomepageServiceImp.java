package com.bloodify.backend.InstitutionManagement.service.classes;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.InstitutionManagement.controller.reponse.PostBrief;
import com.bloodify.backend.InstitutionManagement.service.interfaces.InstHomepageService;
import com.bloodify.backend.UserRequests.model.entities.Post;

import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstHomepageServiceImp implements InstHomepageService {
    @Autowired
    PostDao postDao;

    @Override
    public List<PostBrief> getPostBriefs(String institutionEmail){
        List<Post> posts = postDao.getInstitutionAllPosts(institutionEmail);
        List<PostBrief> postBriefs = new ArrayList<>();
        for(Post post: posts){
            User user = post.getUser();
            postBriefs.add(
                    new PostBrief(post.getPostID(), user.getNationalID(), user.getFirstName()+" "+user.getLastName(),
                            post.getLastTime(), post.getBagsNum(), post.getBloodType())
            );
        }
        return postBriefs;
    }
}
