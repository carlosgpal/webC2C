package com.c2c.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.c2c.model.User;
import com.c2c.repository.UserRepository;
import com.c2c.service.UserService;

public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String iduser) {
        return userRepository.findById(iduser).orElse(null);
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(String iduser, User newUser) {
        return userRepository.findById(iduser)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPass(newUser.getPass());
                    user.setLasttime(newUser.getLasttime());
                    user.setIsverify(newUser.getIsverify());
                    user.setVerifylink(newUser.getVerifylink());
                    user.setProducts(newUser.getProducts());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setIduser(iduser);
                    return userRepository.save(newUser);
                });
    }

    @Override
    public User deleteUser(String iduser) {
        User user = userRepository.findById(iduser).orElse(null);
        userRepository.deleteById(iduser);
        return user;  
    }
}
