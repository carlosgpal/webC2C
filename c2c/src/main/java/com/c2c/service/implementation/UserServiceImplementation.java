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
    public User createOrUpdateUser(String iduser, User newUser) {
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
    public User updateUser(String iduser, User newUser) {
        User user = userRepository.findById(iduser).orElse(null);
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPass(newUser.getPass());
        user.setLasttime(newUser.getLasttime());
        user.setIsverify(newUser.getIsverify());
        user.setVerifylink(newUser.getVerifylink());
        user.setProducts(newUser.getProducts());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User deleteUser(String iduser) {
        User user = userRepository.findById(iduser).orElseThrow(
                () -> new EntityNotFoundException("User not found"));

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

}
