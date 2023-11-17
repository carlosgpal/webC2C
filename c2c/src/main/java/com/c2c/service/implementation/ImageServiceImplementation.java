package com.c2c.service.implementation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c2c.model.Image;
import com.c2c.repository.ImageRepository;
import com.c2c.service.ImageService;

@Service
public class ImageServiceImplementation implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        List<Image> images = imageRepository.findAll();
        if (images.isEmpty()) {
            throw new EntityNotFoundException("No images found");
        }
        return images;
    }

    @Override
    public Image getImageById(String idimage) {
        return imageRepository.findById(idimage)
                .orElseThrow(() -> new EntityNotFoundException("Image with ID: " + idimage + " not found"));
    }

    @Override
    public Image createImage(Image newImage) {
        validateImage(newImage);

        return imageRepository.save(newImage);
    }

    @Override
    public Image createOrUpdateImage(String idimage, Image newImage) {
        validateImage(newImage);

        return imageRepository.findById(idimage)
                .map(image -> {
                    image.setLink(newImage.getLink());
                    image.setProducts(newImage.getProducts());
                    return imageRepository.save(image);
                })
                .orElseGet(() -> {
                    newImage.setIdimage(idimage);
                    return imageRepository.save(newImage);
                });
    }

    @Override
    public Image updateImage(String idimage, Image newImage) {
        validateImage(newImage);

        return imageRepository.findById(idimage)
                .map(image -> {
                    image.setLink(newImage.getLink());
                    image.setProducts(newImage.getProducts());
                    return imageRepository.save(image);
                })
                .orElseThrow(() -> new EntityNotFoundException("Image with ID: " + idimage + " not found"));
    }

    @Override
    @Transactional
    public Image deleteImage(String idimage) {
        Image image = imageRepository.findById(idimage)
                .orElseThrow(() -> new EntityNotFoundException("Image with ID: " + idimage + " not found"));

        image.getProducts().clear();
        imageRepository.save(image);
        imageRepository.delete(image);

        return image;
    }

    private void validateImage(Image image) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }
        if (image.getIdimage() == null || image.getIdimage().trim().isEmpty()) {
            throw new IllegalArgumentException("Image id cannot be null or empty");
        }
        if (image.getLink() == null || image.getLink().trim().isEmpty()) {
            throw new IllegalArgumentException("Image link cannot be null or empty");
        }
    }
}
