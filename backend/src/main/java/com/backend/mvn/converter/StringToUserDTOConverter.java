package com.backend.mvn.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.dto.UserDTO;
import com.backend.service.UserService;

// This is a converter that converts a String to a UserDTO
@Component
public class StringToUserDTOConverter implements Converter<String, UserDTO> {
    @Autowired
    UserService userService;

    @Override
    public UserDTO convert(String id) {
        UserDTO usertDTO = userService.getUserById(id);
        return usertDTO;
    }
}
