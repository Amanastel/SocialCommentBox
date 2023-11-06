package com.comment.controller;

import com.comment.Model.DTO.PostDTO;
import com.comment.Model.DTO.PostWithCommentsDTO;
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

    /**
     * Creates a new post associated with a specific user.
     *
     * @param post     The post to be created.
     * @param username The username of the user creating the post.
     * @return A ResponseEntity with the created Post and HTTP status CREATED.
     */
    @PostMapping("/{username}")
    public ResponseEntity<Post> createPostHandel(@RequestBody Post post, @PathVariable String username) {
        return new ResponseEntity<>(postService.savePost(post, username), HttpStatus.CREATED);
    }

    /**
     * Retrieves a post by its unique ID.
     *
     * @param id The ID of the post to retrieve.
     * @return A ResponseEntity with the retrieved PostDTO and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    /**
     * Deletes a post by its unique ID.
     *
     * @param id The ID of the post to delete.
     * @return A ResponseEntity with the deleted Post and HTTP status OK.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePostByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(postService.DeletePostById(id));
    }

    /**
     * Retrieves a list of posts associated with a specific user by their username.
     *
     * @param username The username of the user whose posts are to be retrieved.
     * @return A ResponseEntity with a list of PostWithCommentsDTOs representing the user's posts and HTTP status OK.
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostWithCommentsDTO>> getPostsByUserNameHandel(@PathVariable String username) {
        return ResponseEntity.ok(postService.getAllPostsByUserName(username));
    }
}
