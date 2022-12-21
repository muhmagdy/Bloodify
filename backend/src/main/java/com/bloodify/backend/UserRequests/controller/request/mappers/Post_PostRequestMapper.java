package com.bloodify.backend.UserRequests.controller.request.mappers;

import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.model.entities.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Post_PostRequestMapper {
    public PostRequest map_to_request(Post post){
        return new PostRequest(post.getPostID(), post.getInstitution().getInstitutionID(),
                post.getStartTime(), post.getLastTime(), post.getBagsNum(), post.getBloodType());
    }
}
