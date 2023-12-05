package com.backend.service;

import java.util.List;

import com.backend.dto.ProductDTO;

// This is the port from the ProductRepository to the ProductController
public interface ProductService {
    public List<ProductDTO> getAllProducts();

    public ProductDTO getProductById(String idproduct);

    public ProductDTO createProduct(ProductDTO newProduct);

    public void deleteProduct(String idproduct);
}
