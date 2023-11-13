package com.c2c.service;

import java.util.List;

import com.c2c.model.User;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(String iduser);
    public User createUser(User newUser);
    public User updateUser(String iduser, User newUser);
    public User deleteUser(String iduser);
}