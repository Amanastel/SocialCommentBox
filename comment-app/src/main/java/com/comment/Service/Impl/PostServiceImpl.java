package com.comment.Service.Impl;

import com.comment.Exceptions.PostException;
import com.comment.Exceptions.UsersException;
import com.comment.Model.DTO.CommentDTO;
import com.comment.Model.DTO.PostDTO;
import com.comment.Model.DTO.PostWithCommentsDTO;
import com.comment.Model.Post;
import com.comment.Model.User;
import com.comment.Repository.CommentRepository;
import com.comment.Repository.PostRepository;
import com.comment.Repository.UserRepository;
import com.comment.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    /**
     * Creates a new post in the database associated with a specific user.
     * Validates the post details and ensures that the user exists.
     *
     * @param post     The post to be created.
     * @param username The username of the user associated with the post.
     * @return The newly created post.
     * @throws PostException if the post details are invalid or if the user is not found.
     */
    @Transactional
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

    /**
     * Retrieves a post by its unique ID and maps it to a PostDTO including its comments.
     *
     * @param id The ID of the post to retrieve.
     * @return A PostDTO representing the post's details and associated comments.
     * @throws PostException if the post is not found.
     */
    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
        List<CommentDTO> commentDTOs = post.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
        return new PostDTO(post, commentDTOs);
    }

    /**
     * Deletes a post by its unique ID.
     *
     * @param id The ID of the post to delete.
     * @return The deleted post.
     * @throws PostException if the post is not found.
     */
    @Override
    public Post DeletePostById(Long id) {
        if (id == null)
            throw new PostException("Invalid post id");

        Post post = postRepository.findById(id).orElseThrow(() -> new PostException("Post not found"));
        postRepository.deleteById(id);
        return post;
    }

    /**
     * Retrieves a list of all posts associated with a specific user and maps them to PostWithCommentsDTO,
     * which includes the associated comments.
     *
     * @param name The username of the user to retrieve posts for.
     * @return A list of PostWithCommentsDTO representing the user's posts and their associated comments.
     * @throws PostException if the username is invalid or if the user is not found.
     */
    @Override
    public List<PostWithCommentsDTO> getAllPostsByUserName(String name) {
        if (name == null) {
            throw new PostException("Invalid user name");
        }

        User user = userRepository.findByUsername(name);
        if (user == null) {
            throw new PostException("User with name: " + name + " not found");
        }

        List<Post> posts = user.getPosts();
        List<PostWithCommentsDTO> postDTOs = posts.stream().map(PostWithCommentsDTO::new).collect(Collectors.toList());

        return postDTOs;
    }
}
