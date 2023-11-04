package com.comment.controller;

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



    @PostMapping
    public ResponseEntity<User> createUserHandel(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsersHandel() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserByIdHandel(@PathVariable Long id) {
        return ResponseEntity.ok(userService.DeleteUserById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByNameHandel(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserName(name));
    }
}