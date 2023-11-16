package com.c2c.service.implementation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String idproduct) {
        return productRepository.findById(idproduct).orElse(null);
    }

    @Override
    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Product createOrUpdateProduct(String idproduct, Product newProduct) {
        return productRepository.findById(idproduct)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setDate(newProduct.getDate());
                    product.setPlace(newProduct.getPlace());
                    product.setImages(newProduct.getImages());
                    product.setTags(newProduct.getTags());
                    product.setUsers(newProduct.getUsers());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setIdproduct(idproduct);
                    return productRepository.save(newProduct);
                });
    }

    @Override
    public Product updateProduct(String idproduct, Product newProduct) {
        Product product = productRepository.findById(idproduct).orElse(null);
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setDate(newProduct.getDate());
        product.setPlace(newProduct.getPlace());
        product.setImages(newProduct.getImages());
        product.setTags(newProduct.getTags());
        product.setUsers(newProduct.getUsers());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product deleteProduct(String idproduct) {
        Product product = productRepository.findById(idproduct).orElseThrow(
                () -> new EntityNotFoundException("Product not found"));

        product.getUsers().clear();
        product.getImages().clear();
        product.getTags().clear();
        productRepository.save(product);

        productRepository.delete(product);
        return product;
    }

}
