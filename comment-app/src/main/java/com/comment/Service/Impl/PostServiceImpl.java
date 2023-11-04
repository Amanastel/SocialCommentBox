package com.comment.Service.Impl;

import com.comment.Exceptions.PostException;
import com.comment.Exceptions.UsersException;
import com.comment.Model.Post;
import com.comment.Model.User;
import com.comment.Repository.CommentRepository;
import com.comment.Repository.PostRepository;
import com.comment.Repository.UserRepository;
import com.comment.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Post savePost(Post post, String username) {
        if (post == null)
            throw new PostException("Invalid post Details");

        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsersException("User with name: " + username + " not found");

        post.setUser(user);
        post.setCreatedOn(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        if (id == null)
            throw new PostException("Invalid post id");

        return postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
    }

    @Override
    public Post DeletePostById(Long id) {
        if (id == null)
            throw new PostException("Invalid post id");

        Post post = postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
        postRepository.deleteById(id);
        return post;
    }

    @Override
    public List<Post> getAllPostsByUserName(String name) {
        if (name == null)
            throw new PostException("Invalid user name");

        User user = userRepository.findByUsername(name);
        if (user == null)
            throw new PostException("User with name: " + name + " not found");

        return user.getPosts();
    }
}
