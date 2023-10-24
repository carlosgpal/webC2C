package com.c2c.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInterface extends JpaRepository<Product, String> {
    // select fields from user where productid = [param]
    Product findByIdproduct(String idproduct);
}
