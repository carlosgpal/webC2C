package com.backend.service.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.ProductElastic;
import com.backend.repository.ProductElasticRepository;
import com.backend.service.ProductElasticService;

// This is a service that implements the methods of the ProductElasticService interface
@Service
public class ProductElasticServiceImplementation implements ProductElasticService {

    @Autowired
    private ProductElasticRepository productElasticRepository;

    // Get all products from Elasticsearch
    @Override
    public List<ProductElastic> getAllProductsElastic() throws IOException {
        Iterable<ProductElastic> productsElasticIterable = productElasticRepository.findAll();
        List<ProductElastic> productsElastic = new ArrayList<ProductElastic>();
        productsElasticIterable.forEach(productsElastic::add);
        if (productsElastic.isEmpty()) {
            throw new EntityNotFoundException("No productsElastic found");
        }
        return productsElastic;
    }

    // Get a product from Elasticsearch by ID
    @Override
    public ProductElastic getProductElasticById(String idproduct) throws IOException {
        return productElasticRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("ProductElastic with ID: " + idproduct + " not found"));
    }

    // Create a new product in Elasticsearch
    @Override
    public ProductElastic createProductElastic(ProductElastic newProduct) throws IOException {
        validateProductElastic(newProduct);

        if (productElasticRepository.existsById(newProduct.getIdproduct())) {
            throw new IllegalArgumentException(
                    "ProductElastic with ID: " + newProduct.getIdproduct()
                            + " already exists. Use the update method instead.");
        }

        return productElasticRepository.save(newProduct);
    }

    // Create or update a product in Elasticsearch
    @Override
    public ProductElastic createOrUpdateProductElastic(String idproduct, ProductElastic newProduct) throws IOException {
        if (productElasticRepository.existsById(idproduct)) {
            return updateProductElastic(idproduct, newProduct);
        } else {
            return createProductElastic(newProduct);
        }
    }

    // Update a product in Elasticsearch
    @Override
    public ProductElastic updateProductElastic(String idproduct, ProductElastic newProduct) throws IOException {
        validateProductElastic(newProduct);

        productElasticRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("ProductElastic with ID: " + idproduct + " not found"));

        newProduct.setIdproduct(idproduct);
        return productElasticRepository.save(newProduct);
    }

    // Delete a product from Elasticsearch
    @Override
    public ProductElastic deleteProductElastic(String idproduct) throws IOException {
        ProductElastic productElastic = productElasticRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("ProductElastic with ID: " + idproduct + " not found"));

        productElasticRepository.delete(productElastic);

        return productElastic;
    }

    // Get products from Elasticsearch by user ID
    @Override
    public List<ProductElastic> getProductsElasticByUser(String iduser) throws IOException {
        List<ProductElastic> productsElastic = productElasticRepository.findByUser(iduser);
        if (productsElastic.isEmpty()) {
            throw new EntityNotFoundException("No productsElastic found for user with ID: " + iduser);
        }
        return productsElastic;
    }

    // Get products from Elasticsearch by tags
    @Override
    public List<ProductElastic> getProductsElasticByTags(List<String> idtags) throws IOException {
        List<ProductElastic> productsElastic = productElasticRepository.findByTagsIn(idtags);
        if (productsElastic.isEmpty()) {
            throw new EntityNotFoundException("No productsElastic found for tags: " + idtags);
        }
        return productsElastic;
    }

    // Get products from Elasticsearch by name or description
    @Override
    public List<ProductElastic> searchProductsByNameOrDescription(String query) throws IOException {
        List<ProductElastic> productsElastic = productElasticRepository.findByNameOrDescription(query);
        if (productsElastic.isEmpty()) {
            throw new EntityNotFoundException("No productsElastic found for query: " + query);
        }
        return productsElastic;
    }

    // Validate the fields of a ProductElastic object
    private void validateProductElastic(ProductElastic productElastic) {
        if (productElastic == null) {
            throw new IllegalArgumentException("ProductElastic cannot be null");
        }
        if (productElastic.getIdproduct() == null || productElastic.getIdproduct().trim().isEmpty()) {
            throw new IllegalArgumentException("ProductElastic id cannot be empty");
        }
        if (productElastic.getName() == null || productElastic.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("ProductElastic name cannot be empty");
        }
        if (productElastic.getDescription() == null || productElastic.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("ProductElastic description cannot be empty");
        }
        if (productElastic.getDate() == null) {
            throw new IllegalArgumentException("ProductElastic date cannot be empty");
        }
        if (productElastic.getPlace() == null || productElastic.getPlace().trim().isEmpty()) {
            throw new IllegalArgumentException("ProductElastic place cannot be empty");
        }
    }

}
