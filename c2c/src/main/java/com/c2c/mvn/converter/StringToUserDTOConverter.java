package com.c2c.mvn.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.c2c.dto.UserDTO;
import com.c2c.service.UserService;

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
