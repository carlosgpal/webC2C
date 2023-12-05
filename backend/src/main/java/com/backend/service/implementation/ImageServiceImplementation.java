package com.backend.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.ImageDTO;
import com.backend.model.Image;
import com.backend.repository.ImageRepository;
import com.backend.repository.ProductRepository;
import com.backend.service.ImageService;
import com.backend.service.exception.ImageNotFoundException;
import com.backend.service.exception.TechnicalException;

// This is a service that implements the methods of the ImageService interface
@Service
public class ImageServiceImplementation implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all images
    @Override
    public List<ImageDTO> getAllImages() {
        List<Image> listImagesEntity = imageRepository.findAll();
        List<ImageDTO> listImageDTO = listImagesEntity.stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .collect(Collectors.toList());
        return listImageDTO;
    }

    // Get image by ID
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

    // Create a new image
    @Override
    public ImageDTO createImage(ImageDTO image) {
        Image imageEntity = modelMapper.map(image, Image.class);
        imageEntity = imageRepository.save(imageEntity);
        return modelMapper.map(imageEntity, ImageDTO.class);
    }

    // Delete an image, when an image is deleted, it is removed from all products
    // but products are not deleted
    @Override
    @Transactional
    public void deleteImage(String idimage) {
        try {
            Image imageEntity = imageRepository.findByIdimage(idimage);

            imageEntity.getProducts().forEach(product -> {
                product = productRepository.findByIdproduct(product.getIdproduct());
                product.getImages().remove(imageEntity);
                productRepository.save(product);
            });
            imageEntity.getProducts().clear();
            imageRepository.delete(imageEntity);

        } catch (EmptyResultDataAccessException e) {
            throw new ImageNotFoundException(idimage, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
}
