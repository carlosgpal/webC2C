package com.c2c.service.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c2c.model.ProductElastic;
import com.c2c.repository.ProductElasticRepository;
import com.c2c.service.ProductElasticService;

@Service
public class ProductElasticServiceImplementation implements ProductElasticService {

    @Autowired
    private ProductElasticRepository productElasticRepository;

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

    @Override
    public ProductElastic getProductElasticById(String idproduct) throws IOException {
        return productElasticRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("ProductElastic with ID: " + idproduct + " not found"));
    }

    @Override
    public ProductElastic createProductElastic(ProductElastic newProduct) throws IOException {
        validateProductElastic(newProduct);

        return productElasticRepository.save(newProduct);
    }

    @Override
    public ProductElastic createOrUpdateProductElastic(String idproduct, ProductElastic newProduct) throws IOException {
        if (productElasticRepository.existsById(idproduct)) {
            return updateProductElastic(idproduct, newProduct);
        } else {
            return createProductElastic(newProduct);
        }
    }

    @Override
    public ProductElastic updateProductElastic(String idproduct, ProductElastic newProduct) throws IOException {
        validateProductElastic(newProduct);

        productElasticRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("ProductElastic with ID: " + idproduct + " not found"));

        newProduct.setIdproduct(idproduct);
        return productElasticRepository.save(newProduct);
    }

    @Override
    public ProductElastic deleteProductElastic(String idproduct) throws IOException {
        ProductElastic productElastic = productElasticRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("ProductElastic with ID: " + idproduct + " not found"));

        productElasticRepository.delete(productElastic);

        return productElastic;
    }

    @Override
    public List<ProductElastic> getProductsElasticByUser(String iduser) throws IOException {
        return productElasticRepository.findByUser(iduser);
    }

    @Override
    public List<ProductElastic> getProductsElasticByTags(List<String> idtags) throws IOException {
        return productElasticRepository.findByTagsIn(idtags);
    }

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
        if (productElastic.getUser() == null || productElastic.getUser().trim().isEmpty()) {
            throw new IllegalArgumentException("ProductElastic users cannot be empty");
        }
    }

}
