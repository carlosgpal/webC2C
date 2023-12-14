package com.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.ProductElastic;
import com.backend.service.ProductElasticService;

// This is the ProductElasticController that handles the requests from the frontend
@RestController
@RequestMapping("/api/elastic")
public class ElasticController {

    @Autowired
    private ProductElasticService productElasticService;

    // Get all products
    @GetMapping
    public List<ProductElastic> getAllProducts() throws IOException {
        return productElasticService.getAllProductsElastic();
    }

    // Get a product by ID
    @GetMapping("/product/{idproduct}")
    public ProductElastic getProduct(@PathVariable String idproduct) throws IOException {
        return productElasticService.getProductElasticById(idproduct);
    }

    // Get products by user
    @GetMapping("/user/{iduser}")
    public List<ProductElastic> getProductByUser(@PathVariable String iduser) throws IOException {
        return productElasticService.getProductsElasticByUser(iduser);
    }

    // Get products by tags /api/elastic/searchByTags?tags=tag1&tags=tag2 (en
    // minuscula)
    @GetMapping("/searchByTags")
    public List<ProductElastic> getProductsByTags(@RequestParam MultiValueMap<String, String> params)
            throws IOException {
        List<String> tags = params.get("tags");
        return productElasticService.getProductsElasticByTags(tags);
    }

    @GetMapping("/search")
    public List<ProductElastic> searchProducts(@RequestParam String query) throws IOException {
        return productElasticService.searchProductsByNameOrDescription(query);
    }

    // Create a new product
    @PostMapping
    public ProductElastic saveProduct(@RequestBody ProductElastic newProduct) throws IOException {
        return productElasticService.createProductElastic(newProduct);
    }

    // Update a product by ID
    @PutMapping("/{idproduct}")
    public ProductElastic updateProduct(@PathVariable String idproduct, @RequestBody ProductElastic newProduct)
            throws IOException {
        return productElasticService.updateProductElastic(idproduct, newProduct);
    }

    // Delete a product by ID
    @DeleteMapping("/{idproduct}")
    public void deleteProduct(@PathVariable String idproduct) throws IOException {
        productElasticService.deleteProductElastic(idproduct);
    }

}
