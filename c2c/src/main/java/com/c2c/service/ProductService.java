package com.c2c.service;

import java.util.List;

import com.c2c.model.Product;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProductById(String idproduct);

    public Product createProduct(Product newProduct);

    public Product createOrUpdateProduct(String idproduct, Product newProduct);

    public Product updateProduct(String idproduct, Product newProduct);

    public Product deleteProduct(String idproduct);
}
