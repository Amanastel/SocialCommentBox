package com.comment.Service.Impl;

import com.comment.Exceptions.CommentException;
import com.comment.Exceptions.PostException;
import com.comment.Exceptions.UsersException;
import com.comment.Model.Comment;
import com.comment.Model.Post;
import com.comment.Model.User;
import com.comment.Repository.CommentRepository;
import com.comment.Repository.PostRepository;
import com.comment.Repository.UserRepository;
import com.comment.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment saveComment(Comment comment, String username, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException("Post not found"));
        if (comment == null)
            throw new CommentException("Invalid comment Details");

        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsersException("User with name: " + username + " not found");

        comment.setCommentDatetime(LocalDateTime.now());

        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);

    }

    @Override
    public Comment getCommentById(Long id) {
        if (id == null)
            throw new CommentException("Invalid comment id");
        return commentRepository.findById(id).orElseThrow(() -> new CommentException("Comment not found"));
    }

    @Override
    public Comment DeleteCommentById(Long id) {
        if (id == null)
            throw new CommentException("Invalid comment id");
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException("Comment not found"));
        commentRepository.deleteById(id);
        return comment;
    }

    @Override
    public List<Comment> getAllCommentsByUserName(String name)
    {
        if (name == null)
            throw new UsersException("Invalid user name");

        User user = userRepository.findByUsername(name);
        if (user == null)
            throw new UsersException("User with name: " + name + " not found");

        Post post = postRepository.findById(user.getUserId()).orElseThrow(() -> new PostException("Post not found"));
        List<Comment> comments = post.getComments();

        if (comments.isEmpty())
            throw new CommentException("No comments found");

        return comments;
    }

    @Override
    public List<Comment> getAllCommentsByPostId(Long id) {
        if (id == null)
            throw new PostException("Invalid post id");

        Post post = postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
        List<Comment> comments = post.getComments();
        if (comments.isEmpty())
            throw new CommentException("No comments found");
        return comments;
    }
}
