package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Image;

// This is the repository for the Image model of the MySQL database
@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByIdimage(String idimage);

    // This is a custom query that returns a list of images that match the ids
    // passed
    @Query("select i from Image i where i.idimage in :ids")
    List<Image> findByImages(@Param("ids") List<String> ids);
}