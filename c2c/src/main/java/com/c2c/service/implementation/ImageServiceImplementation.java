package com.c2c.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.c2c.model.Image;
import com.c2c.repository.ImageRepository;
import com.c2c.service.ImageService;

public class ImageServiceImplementation implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImagetById(String idproduct) {
        return imageRepository.findById(idproduct).orElse(null);
    }

    @Override
    public Image createImage(Image newImage) {
        return imageRepository.save(newImage);
    }

    @Override
    public Image updateImage(String idimage, Image newImage) {
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
    public Image deleteImage(String idimage) {
        Image image = imageRepository.findById(idimage).orElse(null);
        imageRepository.deleteById(idimage);
        return image;
    }
    
}
