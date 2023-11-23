package com.c2c.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.c2c.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByIdproduct(String idproduct);

    @Query("select p from Product p where p.idproduct in :ids")
    List<Product> findByProducts(@Param("ids") List<String> ids);
}