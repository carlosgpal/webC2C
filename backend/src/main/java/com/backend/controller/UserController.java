package com.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserDTO;
import com.backend.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

// This is the UserController that handles the requests from the frontend
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by Id
    @GetMapping("/{iduser}")
    public UserDTO getUserById(@PathVariable String iduser) {
        return userService.getUserById(iduser);
    }

    // Create a New User
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO newUser) {
        return userService.createUser(newUser);
    }

}
