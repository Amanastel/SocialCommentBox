package com.comment.controller;

import com.comment.Model.DTO.UserDTO;
import com.comment.Model.User;
import com.comment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     * @return A ResponseEntity with the created User and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<User> createUserHandel(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A ResponseEntity with the retrieved UserDTO and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A ResponseEntity with a list of UserDTOs representing all users and HTTP status OK.
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsersHandel() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id The ID of the user to delete.
     * @return A ResponseEntity with the deleted User and HTTP status OK.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.DeleteUserById(id));
    }

    /**
     * Retrieves a user by their username.
     *
     * @param name The username of the user to retrieve.
     * @return A ResponseEntity with the retrieved UserDTO and HTTP status OK.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<UserDTO> getUserByNameHandel(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserName(name));
    }
}
