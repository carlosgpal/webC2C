package com.backend.mvn.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.dto.ImageDTO;
import com.backend.service.ImageService;

// This is a converter that converts a String to an ImageDTO
@Component
public class StringToImageDTOConverter implements Converter<String, ImageDTO> {
    @Autowired
    ImageService imageService;

    @Override
    public ImageDTO convert(String id) {
        ImageDTO imageDTO = imageService.getImageById(id);
        return imageDTO;
    }
}
