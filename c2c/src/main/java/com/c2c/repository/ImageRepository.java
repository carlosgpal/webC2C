package com.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2c.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByIdimage(String idimage);
}