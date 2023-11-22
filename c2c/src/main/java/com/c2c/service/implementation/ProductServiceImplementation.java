package com.c2c.service.implementation;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c2c.model.Product;
import com.c2c.repository.ProductRepository;
import com.c2c.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found");
        }
        return products;
    }

    @Override
    @Transactional
    public Product getProductById(String idproduct) {
        Product product = productRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID: " + idproduct + " not found"));
        return product;
    }

    @Override
    public Product createProduct(Product newProduct) {
        validateProduct(newProduct);

        return productRepository.save(newProduct);
    }

    @Override
    public Product createOrUpdateProduct(String idproduct, Product newProduct) {
        if (productRepository.existsById(idproduct)) {
            return updateProduct(idproduct, newProduct);
        } else {
            return createProduct(newProduct);
        }
    }

    @Override
    public Product updateProduct(String idproduct, Product newProduct) {
        validateProduct(newProduct);

        productRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID: " + idproduct + " not found"));

        newProduct.setIdproduct(idproduct);
        return productRepository.save(newProduct);
    }

    @Override
    @Transactional
    public Product deleteProduct(String idproduct) {
        Product product = productRepository.findById(idproduct)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID: " + idproduct + " not found"));

        product.getUsers().clear();
        product.getImages().clear();
        product.getTags().clear();
        productRepository.save(product);

        productRepository.delete(product);

        return product;
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getIdproduct() == null || product.getIdproduct().trim().isEmpty()) {
            throw new IllegalArgumentException("Product id cannot be empty");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be empty");
        }
        if (product.getDate() == null) {
            throw new IllegalArgumentException("Product date cannot be empty");
        }
        if (product.getPlace() == null || product.getPlace().trim().isEmpty()) {
            throw new IllegalArgumentException("Product place cannot be empty");
        }
    }
}
