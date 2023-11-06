package com.comment.Service;

import com.comment.Model.DTO.UserDTO;
import com.comment.Model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public UserDTO getUserById(Long id);
    public UserDTO getUserName(String name);
    public List<UserDTO> getAllUsers();
    public User DeleteUserById(Long id);

}
