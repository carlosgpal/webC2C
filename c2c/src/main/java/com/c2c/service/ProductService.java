package com.c2c.service;

import java.util.List;

import com.c2c.dto.ProductDTO;

public interface ProductService {
    public List<ProductDTO> getAllProducts();

    public ProductDTO getProductById(String idproduct);

    public ProductDTO createProduct(ProductDTO newProduct);

    public void deleteProduct(String idproduct);
}
