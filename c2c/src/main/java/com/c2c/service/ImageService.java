package com.c2c.service;

import java.util.List;

import com.c2c.model.Image;

public interface ImageService {
    public List<Image> getAllImages();

    public Image getImageById(String idproduct);

    public Image createImage(Image newImage);

    public Image createOrUpdateImage(String idimage, Image newImage);

    public Image updateImage(String idimage, Image newImage);

    public Image deleteImage(String idimage);
}
