package com.comment.controller;

import com.comment.Model.Post;
import com.comment.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{username}")
    public ResponseEntity<Post> createPostHandel(@RequestBody Post post, @PathVariable String username) {
        return new ResponseEntity<>(postService.savePost(post,username), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePostByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(postService.DeletePostById(id));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Post>> getPostsByUserNameHandel(@PathVariable String username) {
        return ResponseEntity.ok(postService.getAllPostsByUserName(username));
    }


}
