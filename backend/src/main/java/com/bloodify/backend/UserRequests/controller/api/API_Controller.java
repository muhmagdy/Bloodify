package com.bloodify.backend.UserRequests.controller.api;

import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.dto.mapper.Dto_PostRequest_Mapper;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PostResponse<String>> addPost(@RequestBody PostRequest request, Authentication auth) {
        PostDto postDto = this.mapper.mapToPostDto(request, auth.getName());
        boolean status = this.postService.savePost(postDto);
        if (status)
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new PostResponse<>(true, "Your Request Posted Successfully"));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new PostResponse<>(false, "Failed to Post Your Request"));
    }

    @PutMapping("/edit")
    public ResponseEntity<PostResponse<String>> editPost(@RequestBody PostRequest request, Authentication auth) {
        PostDto postDto = this.mapper.mapToPostDto(request, auth.getName());
        boolean status = this.postService.updatePost(postDto);
        if (status)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PostResponse<>(true, "Your Request Edited Successfully"));
        return ResponseEntity.status(422).body(new PostResponse<>(false, "Failed to Edit Your Request"));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<PostResponse<String>> deletePost(@PathVariable("postId") int id, Authentication auth) {
        boolean status = this.postService.deletePost(id, auth.getName());
        if (status)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PostResponse<>(true, "Your Request Deleted Successfully"));
        return ResponseEntity.status(422).body(new PostResponse<>(false, "Failed to Delete Your Request"));
    }

    @GetMapping("/userPosts")
    public ResponseEntity<PostResponse<List<PostRequest>>> getLatestUserRequest(Authentication auth) {
        List<PostRequest> userPosts = this.postService.getUserPosts(auth.getName());
        boolean status = userPosts.size() != 0;
        if (status)
            return ResponseEntity.status(HttpStatus.OK).body(new PostResponse<>(true, userPosts));
        return ResponseEntity.status(422).body(new PostResponse<>(false, null));
    }
}