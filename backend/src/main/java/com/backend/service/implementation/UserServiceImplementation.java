package com.backend.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.ProductDTO;
import com.backend.dto.UserDTO;
import com.backend.model.Product;
import com.backend.model.User;
import com.backend.repository.ProductRepository;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;
import com.backend.service.exception.TechnicalException;
import com.backend.service.exception.UserNotFoundException;

import org.modelmapper.ModelMapper;

// This is a service that implements the methods of the UserService interface
@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        // Retrieve all users from the repository
        List<User> listUsersEntity = userRepository.findAll();
        // Map the User entities to UserDTO objects using ModelMapper
        List<UserDTO> listUserDTO = listUsersEntity.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return listUserDTO;
    }

    @Override
    public UserDTO getUserById(String iduser) {
        UserDTO userDTO = null;
        try {
            // Find the user entity by id
            User userEntity = userRepository.findByIduser(iduser);
            // Map the User entity to UserDTO object using ModelMapper
            userDTO = modelMapper.map(userEntity, UserDTO.class);
        } catch (EntityNotFoundException e) {
            // Throw UserNotFoundException if the user is not found
            throw new UserNotFoundException(iduser, e);
        } catch (Exception e) {
            // Throw TechnicalException for any other exception
            throw new TechnicalException(e);
        }
        return userDTO;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        // Map the UserDTO object to User entity using ModelMapper
        User userEntity = modelMapper.map(user, User.class);

        if (user.getIduser() == null) {
            // If the user does not have an id, set the products by finding them in the
            // repository
            List<String> ids = new ArrayList<String>();
            for (ProductDTO product : user.getProducts()) {
                ids.add(product.getIdproduct());
            }
            userEntity.setProducts(productRepository.findByProducts(ids));
        } else {
            // If the user has an id, find the existing user entity and update it with the
            // new values
            userEntity = userRepository.findByIduser(user.getIduser());
            userEntity = modelMapper.map(user, User.class);
        }
        // Save the user entity in the repository and map it back to UserDTO object
        // using ModelMapper
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    @Transactional
    public void deleteUser(String iduser) {
        try {
            // Find the user entity by id
            User userEntity = userRepository.findByIduser(iduser);
            for (Product product : userEntity.getProducts()) {
                // Find the product entity and clear its associations
                product = productRepository.findByIdproduct(product.getIdproduct());
                product.getUsers().clear();
                product.getImages().clear();
                product.getTags().clear();
                // Save and delete the product entity
                productRepository.save(product);
                productRepository.delete(product);
            }
            // Clear the user's products and save the user entity
            userEntity.getProducts().clear();
            userRepository.save(userEntity);
            // Delete the user entity
            userRepository.delete(userEntity);
        } catch (EmptyResultDataAccessException e) {
            // Throw UserNotFoundException if the user is not found
            throw new UserNotFoundException(iduser, e);
        } catch (Exception e) {
            // Throw TechnicalException for any other exception
            throw new TechnicalException(e);
        }
    }
}
