package com.bloodify.backend.UserRequests.controller.api;

import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.dto.mapper.Dto_PostRequest_Mapper;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/posting")
public class API_Controller {
    @Autowired
    private PostService postService;
    @Autowired
    private Dto_PostRequest_Mapper mapper;

    @PostMapping("/add_post")
    public PostResponse<String> addPost(@RequestBody PostRequest request){
        PostDto postDto = this.mapper.mapToPostDto(request);
        boolean status = this.postService.savePost(postDto);
        return new PostResponse<>(status, status? "Your Request Posted Successfully" : "Failed to Post Your Request");
    }

    @PutMapping("/edit_post/{id}")
    public PostResponse<String> editPost(@RequestBody PostRequest request, @PathVariable("id") int postID){
        PostDto postDto = this.mapper.mapToPostDto(request);
        boolean status = this.postService.updatePost(postDto, postID);
        return new PostResponse<>(status, status? "Your Request Edited Successfully" : "Failed to Edit Your Request");
    }
    @PutMapping("/delete_post")
    public PostResponse<String> deletePost(@RequestBody PostRequest request){
        PostDto postDto = this.mapper.mapToPostDto(request);
        boolean status = this.postService.deletePost(postDto);
        return new PostResponse<>(status, status? "Your Request Deleted Successfully" : "Failed to Delete Your Request");
    }

    @GetMapping("get_posts/{email}")
    public PostResponse<List<PostRequest>> getLatestUserRequest(@PathVariable("email") String userEmail){
        List<PostRequest> userPosts = this.postService.getUserPosts(userEmail);
        boolean status = userPosts.size() != 0;
        return new PostResponse<>(status, status? userPosts : null);
    }

    @GetMapping("/get_post_id")
    public PostResponse<Integer> getPostID(@RequestBody PostRequest request){
        PostDto postDto = this.mapper.mapToPostDto(request);
        int postID = this.postService.getPostID(postDto);
        return new PostResponse<>(postID != -1, postID);
    }
}