package com.c2c.mvn.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.c2c.dto.ImageDTO;
import com.c2c.service.ImageService;

public class StringToImageDTOConverter implements Converter<String, ImageDTO> {
    @Autowired
    ImageService imageService;

    @Override
    public ImageDTO convert(String id) {
        ImageDTO imageDTO = imageService.getImageById(id);
        return imageDTO;
    }
}
