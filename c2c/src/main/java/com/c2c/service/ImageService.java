package com.c2c.service;

import java.util.List;

import com.c2c.dto.ImageDTO;

public interface ImageService {
    public List<ImageDTO> getAllImages();

    public ImageDTO getImageById(String idproduct);

    public ImageDTO createImage(ImageDTO newImage);

    public void deleteImage(String idimage);
}
