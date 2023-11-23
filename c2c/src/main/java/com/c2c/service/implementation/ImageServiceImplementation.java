package com.c2c.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.c2c.dto.ImageDTO;
import com.c2c.model.Image;
import com.c2c.repository.ImageRepository;
import com.c2c.service.ImageService;
import com.c2c.service.exception.ImageNotFoundException;
import com.c2c.service.exception.TechnicalException;

@Service
public class ImageServiceImplementation implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ImageDTO> getAllImages() {
        List<Image> listImagesEntity = imageRepository.findAll();
        List<ImageDTO> listImageDTO = listImagesEntity.stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .collect(Collectors.toList());
        return listImageDTO;
    }

    @Override
    public ImageDTO getImageById(String idimage) {
        ImageDTO imageDTO = null;
        try {
            Image imageEntity = imageRepository.findByIdimage(idimage);
            imageDTO = modelMapper.map(imageEntity, ImageDTO.class);
        } catch (EntityNotFoundException e) {
            throw new ImageNotFoundException(idimage, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        return imageDTO;
    }

    @Override
    public ImageDTO createImage(ImageDTO image) {
        Image imageEntity = modelMapper.map(image, Image.class);
        imageEntity = imageRepository.save(imageEntity);
        return modelMapper.map(imageEntity, ImageDTO.class);
    }

    @Override
    public void deleteImage(String idimage) {
        try {
            imageRepository.deleteById(idimage);
        } catch (EmptyResultDataAccessException e) {
            throw new ImageNotFoundException(idimage, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
}
