package com.comment.Service;

import com.comment.Model.Comment;

import java.util.List;

public interface CommentService {
    public Comment saveComment(Comment comment, String username, Long postId);
    public Comment getCommentById(Long id);
    public Comment DeleteCommentById(Long id);
    public List<Comment> getAllCommentsByUserName(String name);
    public List<Comment> getAllCommentsByPostId(Long id);
}
