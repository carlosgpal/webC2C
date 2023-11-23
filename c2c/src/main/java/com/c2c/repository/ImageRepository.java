package com.c2c.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.c2c.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByIdimage(String idimage);

    @Query("select i from Image i where i.idimage in :ids")
    List<Image> findByImages(@Param("ids") List<String> ids);
}