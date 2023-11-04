package com.comment.Service.Impl;

import com.comment.Exceptions.UsersException;
import com.comment.Model.User;
import com.comment.Repository.CommentRepository;
import com.comment.Repository.PostRepository;
import com.comment.Repository.UserRepository;
import com.comment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public User saveUser(User user) {
        if (user == null)

            throw new UsersException("Invalid users Details");

       User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null)
            throw new UsersException("User with email: " + user.getEmail() + " already exists");

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsersException("User not found"));
    }

    @Override
    public User getUserName(String name) {
        if (name == null)
            throw new UsersException("Invalid user name");

        User user = userRepository.findByUsername(name);
        if (user == null)
            throw new UsersException("User with name: " + name + " not found");

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            throw new UsersException("No users found");
        return users;
    }

    @Override
    public User DeleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsersException("User not found"));
        userRepository.deleteById(id);
        return user;
    }


}
