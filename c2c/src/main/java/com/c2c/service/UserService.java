package com.c2c.service;

import java.util.List;

import com.c2c.dto.UserDTO;

public interface UserService {
    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(String iduser);

    public UserDTO createUser(UserDTO newUser);

    public void deleteUser(String iduser);
}