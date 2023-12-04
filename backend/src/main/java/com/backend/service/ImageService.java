package com.backend.service;

import java.util.List;

import com.backend.dto.ImageDTO;

public interface ImageService {
    public List<ImageDTO> getAllImages();

    public ImageDTO getImageById(String idproduct);

    public ImageDTO createImage(ImageDTO newImage);

    public void deleteImage(String idimage);
}
