package com.comment.Service;

import com.comment.Model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public User getUserById(Long id);
    public User getUserName(String name);
    public List<User> getAllUsers();
    public User DeleteUserById(Long id);

}
