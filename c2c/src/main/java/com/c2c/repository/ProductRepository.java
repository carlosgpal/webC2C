package com.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2c.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByIdproduct(String idproduct);
}