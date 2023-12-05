package com.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ProductDTO;
import com.backend.service.ProductService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// This is the ProductController that handles the requests from the frontend
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by Id
    @GetMapping("/{idproduct}")
    public ProductDTO getProductById(@PathVariable String idproduct) {
        return productService.getProductById(idproduct);
    }

    // Create a new product
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO newProduct) {
        return productService.createProduct(newProduct);
    }
}
