package com.comment.controller;

import com.comment.Model.Comment;
import com.comment.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/{username}")
    public ResponseEntity<Comment> createCommentHandel(@RequestBody Comment comment, @PathVariable Long postId, @PathVariable String username) {
        return new ResponseEntity<>(commentService.saveComment(comment, username, postId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentByIdHandel(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteCommentByIdHandel(@PathVariable Long id) {
        Comment deletedComment = commentService.DeleteCommentById(id);
        return ResponseEntity.ok(deletedComment);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Comment>> getCommentsByUserNameHandel(@PathVariable String username) {
        List<Comment> comments = commentService.getAllCommentsByUserName(username);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostIdHandel(@PathVariable Long postId) {
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

}
