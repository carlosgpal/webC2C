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
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(String idproduct) {
        return imageRepository.findById(idproduct).orElse(null);
    }

    @Override
    public Image createImage(Image newImage) {
        return imageRepository.save(newImage);
    }

    @Override
    public Image createOrUpdateImage(String idimage, Image newImage) {
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
        Image image = imageRepository.findById(idimage).orElse(null);
        image.setLink(newImage.getLink());
        image.setProducts(newImage.getProducts());
        return imageRepository.save(image);
    }

    @Override
    @Transactional
    public Image deleteImage(String idimage) {
        Image image = imageRepository.findById(idimage).orElseThrow(
                () -> new EntityNotFoundException("Tag not found"));

        image.getProducts().clear();
        imageRepository.save(image);

        imageRepository.delete(image);
        return image;
    }

}
