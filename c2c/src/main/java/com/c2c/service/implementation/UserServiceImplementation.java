package com.c2c.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.c2c.dto.ProductDTO;
import com.c2c.dto.UserDTO;
import com.c2c.model.User;
import com.c2c.repository.ProductRepository;
import com.c2c.repository.UserRepository;
import com.c2c.service.UserService;
import com.c2c.service.exception.TechnicalException;
import com.c2c.service.exception.UserNotFoundException;

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
    public void deleteUser(String iduser) {
        try {
            userRepository.deleteById(iduser);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(iduser, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
}
