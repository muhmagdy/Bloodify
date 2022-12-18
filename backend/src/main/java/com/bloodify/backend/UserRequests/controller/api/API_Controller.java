package com.bloodify.backend.UserRequests.controller.api;

import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.dto.mapper.Dto_PostRequest_Mapper;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/user/posting")
public class API_Controller {
    @Autowired
    private PostService postService;
    @Autowired
    private Dto_PostRequest_Mapper mapper;

    @PostMapping("/add")
    public PostResponse<String> addPost(@RequestBody PostRequest request, Authentication auth){
        PostDto postDto = this.mapper.mapToPostDto(request, auth.getName());
        boolean status = this.postService.savePost(postDto);
        return new PostResponse<>(status, status? "Your Request Posted Successfully" : "Failed to Post Your Request");
    }

    @PutMapping("/edit")
    public PostResponse<String> editPost(@RequestBody PostRequest request, Authentication auth){
        PostDto postDto = this.mapper.mapToPostDto(request, auth.getName());
        boolean status = this.postService.updatePost(postDto);
        return new PostResponse<>(status, status? "Your Request Edited Successfully" : "Failed to Edit Your Request");
    }
    @DeleteMapping("/delete/{postId}")
    public PostResponse<String> deletePost(@PathVariable("postId") int id, Authentication auth){
        boolean status = this.postService.deletePost(id, auth.getName());
        return new PostResponse<>(status, status? "Your Request Deleted Successfully" : "Failed to Delete Your Request");
    }
    @GetMapping("/userPosts")
    public PostResponse<List<PostRequest>> getLatestUserRequest(Authentication auth){
        List<PostRequest> userPosts = this.postService.getUserPosts(auth.getName());
        boolean status = userPosts.size() != 0;
        return new PostResponse<>(status, status? userPosts : null);
    }
//    @GetMapping("/post/id")
//    public PostResponse<Integer> getPostID(@RequestBody PostRequest request, Authentication auth){
//        PostDto postDto = this.mapper.mapToPostDto(request, auth.getName());
//        int postID = this.postService.getPostID(postDto);
//        return new PostResponse<>(postID != -1, postID);
//    }
}