package com.backend.service;

import java.util.List;

import com.backend.dto.UserDTO;

public interface UserService {
    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(String iduser);

    public UserDTO createUser(UserDTO newUser);

    public void deleteUser(String iduser);
}