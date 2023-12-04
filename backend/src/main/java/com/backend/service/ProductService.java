package com.backend.service;

import java.util.List;

import com.backend.dto.ProductDTO;

public interface ProductService {
    public List<ProductDTO> getAllProducts();

    public ProductDTO getProductById(String idproduct);

    public ProductDTO createProduct(ProductDTO newProduct);

    public void deleteProduct(String idproduct);
}
