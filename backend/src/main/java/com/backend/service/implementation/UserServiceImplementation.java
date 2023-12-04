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
        List<User> listUsersEntity = userRepository.findAll();
        List<UserDTO> listUserDTO = listUsersEntity.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return listUserDTO;
    }

    @Override
    public UserDTO getUserById(String iduser) {
        UserDTO userDTO = null;
        try {
            User userEntity = userRepository.findByIduser(iduser);
            userDTO = modelMapper.map(userEntity, UserDTO.class);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(iduser, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        return userDTO;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        User userEntity = modelMapper.map(user, User.class);

        if (user.getIduser() == null) {
            List<String> ids = new ArrayList<String>();
            for (ProductDTO product : user.getProducts()) {
                ids.add(product.getIdproduct());
            }
            userEntity.setProducts(productRepository.findByProducts(ids));
        } else {
            userEntity = userRepository.findByIduser(user.getIduser());
            userEntity = modelMapper.map(user, User.class);
        }
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    @Transactional
    public void deleteUser(String iduser) {
        try {
            User userEntity = userRepository.findByIduser(iduser);
            for (Product product : userEntity.getProducts()) {
                product = productRepository.findByIdproduct(product.getIdproduct());
                product.getUsers().clear();
                product.getImages().clear();
                product.getTags().clear();
                productRepository.save(product);
                productRepository.delete(product);
            }
            userEntity.getProducts().clear();
            userRepository.save(userEntity);
            userRepository.delete(userEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(iduser, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
}
