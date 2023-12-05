package com.backend.service;

import java.util.List;

import com.backend.dto.ImageDTO;

// This is the port from the ImageRepository to the ImageController
public interface ImageService {
    public List<ImageDTO> getAllImages();

    public ImageDTO getImageById(String idproduct);

    public ImageDTO createImage(ImageDTO newImage);

    public void deleteImage(String idimage);
}
