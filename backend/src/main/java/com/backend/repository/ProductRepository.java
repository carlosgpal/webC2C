package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Product;

// This is the repository for the Product model of the MySQL database
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByIdproduct(String idproduct);

    // This is a custom query that returns a list of products that match the ids
    // passed
    @Query("select p from Product p where p.idproduct in :ids")
    List<Product> findByProducts(@Param("ids") List<String> ids);
}