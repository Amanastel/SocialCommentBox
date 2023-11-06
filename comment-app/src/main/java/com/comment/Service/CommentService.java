package com.comment.Service;

import com.comment.Model.Comment;
import com.comment.Model.DTO.CommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO saveComment(Comment comment, String username, Long postId);
    public Comment getCommentById(Long id);
    public Comment DeleteCommentById(Long id);
    public List<CommentDTO> getAllCommentsByUserName(String name);
    public List<Comment> getAllCommentsByPostId(Long id);
}
