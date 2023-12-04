package com.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.UserDTO;
import com.backend.model.Product;
import com.backend.model.User;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;

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

    // // Update a User
    // @PutMapping("/{iduser}")
    // public ResponseEntity<User> updateUser(@PathVariable String iduser,
    // @RequestBody User updatedUser) {
    // User user = userIf.findByIduser(iduser);
    // if (user == null) {
    // return ResponseEntity.notFound().build();
    // }
    // user.setName(updatedUser.getName());
    // user.setPass(updatedUser.getPass());
    // user.setLasttime(updatedUser.getLasttime());
    // user.setIsverify(updatedUser.getIsverify());
    // user.setVerifylink(updatedUser.getVerifylink());
    // userIf.save(user);
    // return ResponseEntity.ok(user);
    // }

    // // Delete a User
    // @DeleteMapping("/{iduser}")
    // public ResponseEntity<Void> deleteUser(@PathVariable String iduser) {
    // User user = userIf.findByIduser(iduser);
    // if (user == null) {
    // return ResponseEntity.notFound().build();
    // }
    // userIf.delete(user);
    // return ResponseEntity.noContent().build();
    // }
}
