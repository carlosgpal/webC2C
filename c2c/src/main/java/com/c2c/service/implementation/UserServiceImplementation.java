package com.c2c.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c2c.model.Product;
import com.c2c.model.User;
import com.c2c.repository.ProductRepository;
import com.c2c.repository.UserRepository;
import com.c2c.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found");
        }
        return users;
    }

    @Override
    public User getUserById(String iduser) {
        return userRepository.findById(iduser)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + iduser + " not found"));
    }

    @Override
    public User createUser(User newUser) {
        validateUser(newUser);

        return userRepository.save(newUser);
    }

    @Override
    public User createOrUpdateUser(String iduser, User newUser) {
        if (userRepository.existsById(iduser)) {
            return updateUser(iduser, newUser);
        } else {
            return createUser(newUser);
        }
    }

    @Override
    public User updateUser(String iduser, User newUser) {
        validateUser(newUser);

        userRepository.findById(iduser)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + iduser + " not found"));

        newUser.setIduser(iduser);
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public User deleteUser(String iduser) {
        User user = userRepository.findById(iduser)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + iduser + " not found"));

        List<Product> products = new ArrayList<>(user.getProducts());
        products.forEach(product -> {
            product.getUsers().clear();
            product.getImages().clear();
            product.getTags().clear();

            productRepository.save(product);

            productRepository.delete(product);
        });
        user.getProducts().clear();
        userRepository.save(user);
        userRepository.delete(user);

        return user;
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getIduser() == null || user.getIduser().trim().isEmpty()) {
            throw new IllegalArgumentException("User id cannot be null or empty");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }
        if (user.getPass() == null || user.getPass().trim().isEmpty()) {
            throw new IllegalArgumentException("User pass cannot be null or empty");
        }
        if (user.getLasttime() == null) {
            throw new IllegalArgumentException("User lasttime cannot be null or empty");
        }
        if (user.getVerifylink() == null || user.getVerifylink().trim().isEmpty()) {
            throw new IllegalArgumentException("User verifylink cannot be null or empty");
        }
        if (user.getProducts() == null || user.getProducts().isEmpty()) {
            throw new IllegalArgumentException("User products cannot be null or empty");
        }
    }
}
