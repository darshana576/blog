package org.springboot.blog.controller;

import org.springboot.blog.dto.PostDto;
import org.springboot.blog.model.Post;
import org.springboot.blog.service.PostService;
import org.springboot.blog.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // ✅ Create new post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        Post createdPost = postService.createPost(postDto);
        PostDto response = new PostDto(
                createdPost.getTitle(),
                createdPost.getContent(),
                createdPost.getUser().getId(),
                createdPost.getCategory().getId()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ Get all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // ✅ Get post by ID
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    // ✅ Update post
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        Post updatedPost = postService.updatePost(postId, postDto);
        return ResponseEntity.ok(updatedPost);
    }

    // ✅ Delete post
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse("Post deleted successfully", true));
    }
}