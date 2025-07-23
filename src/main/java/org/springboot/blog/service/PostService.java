package org.springboot.blog.service;

import org.springboot.blog.dto.PostDto;
import org.springboot.blog.exception.ResourceNotFoundException;
import org.springboot.blog.model.Post;
import org.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // ✅ Create Post
    public Post createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        // Optional: If PostDto has author
        return postRepository.save(post);
    }

    // ✅ Get All Posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // ✅ Get Post by ID
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
    }

    // ✅ Update Post
    public Post updatePost(Long postId, PostDto postDto) {
        Post post = getPostById(postId);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return postRepository.save(post);
    }

    // ✅ Delete Post
    public void deletePost(Long postId) {
        Post post = getPostById(postId);
        postRepository.delete(post);
    }
}