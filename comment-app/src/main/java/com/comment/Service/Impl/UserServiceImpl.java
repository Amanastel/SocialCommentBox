package com.comment.Service.Impl;

import com.comment.Exceptions.UsersException;
import com.comment.Model.DTO.PostDTO;
import com.comment.Model.DTO.UserDTO;
import com.comment.Model.Post;
import com.comment.Model.User;
import com.comment.Repository.CommentRepository;
import com.comment.Repository.PostRepository;
import com.comment.Repository.UserRepository;
import com.comment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;


    /**
     * Creates a new user in the database.
     * Validates the user details and ensures that no user with the same email already exists.
     *
     * @param user The user to be created.
     * @return The newly created user.
     * @throws UsersException if the user details are invalid or if a user with the same email already exists.
     */
    @Transactional
    @Override
    public User saveUser(User user) {
        if (user == null)

            throw new UsersException("Invalid users Details");

       User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null)
            throw new UsersException("User with email: " + user.getEmail() + " already exists");

        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their unique ID and maps it to a UserDTO including their posts.
     *
     * @param id The ID of the user to retrieve.
     * @return A UserDTO representing the user's details and posts.
     * @throws UsersException if the user is not found.
     */
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsersException("User not found"));
        List<PostDTO> postDTOs = user.getPosts().stream().map(PostDTO::new).collect(Collectors.toList());
        return new UserDTO(user, postDTOs);
    }

    /**
     * Retrieves a user by their username and maps it to a UserDTO including their posts.
     *
     * @param name The username of the user to retrieve.
     * @return A UserDTO representing the user's details and posts.
     * @throws UsersException if the username is invalid or if the user is not found.
     */
    @Override
    public UserDTO getUserName(String name) {
        if (name == null)
            throw new UsersException("Invalid user name");

        User user = userRepository.findByUsername(name);
        if (user == null)
            throw new UsersException("User with name: " + name + " not found");

        List<PostDTO> postDTOs = user.getPosts().stream().map(PostDTO::new).collect(Collectors.toList());
        return new UserDTO(user, postDTOs);
    }

    /**
     * Retrieves a list of all users in the database, and for each user, maps them to a UserDTO including their posts.
     *
     * @return A list of UserDTOs representing users and their posts.
     * @throws UsersException if no users are found.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UsersException("No users found");
        }

        List<UserDTO> userDTOs = users.stream()
                .map(user -> {
                    List<Post> userPosts = user.getPosts();
                    List<PostDTO> postDTOs = userPosts.stream().map(PostDTO::new).collect(Collectors.toList());
                    return new UserDTO(user, postDTOs);
                })
                .collect(Collectors.toList());

        return userDTOs;
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id The ID of the user to delete.
     * @return The deleted user.
     * @throws UsersException if the user is not found.
     */
    @Override
    public User DeleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsersException("User not found"));
        userRepository.deleteById(id);
        return user;
    }


}
