package com.backend.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.ImageDTO;
import com.backend.dto.ProductDTO;
import com.backend.dto.TagDTO;
import com.backend.model.Product;
import com.backend.repository.ImageRepository;
import com.backend.repository.ProductRepository;
import com.backend.repository.TagRepository;
import com.backend.service.ProductService;
import com.backend.service.exception.ProductNotFoundException;
import com.backend.service.exception.TechnicalException;

// This is a service that implements the methods of the ProductService interface
@Service
// @Transactional
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        // Retrieve all products from the database
        List<Product> listProductsEntity = productRepository.findAll();
        // Convert the list of Product entities to a list of ProductDTOs using
        // ModelMapper
        List<ProductDTO> listProductDTO = listProductsEntity.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return listProductDTO;
    }

    @Override
    public ProductDTO getProductById(String idproduct) {
        ProductDTO productDTO = null;
        try {
            // Retrieve the product entity by its ID
            Product productEntity = productRepository.findByIdproduct(idproduct);
            // Convert the product entity to a ProductDTO using ModelMapper
            productDTO = modelMapper.map(productEntity, ProductDTO.class);
        } catch (EntityNotFoundException e) {
            // Throw a ProductNotFoundException if the product is not found
            throw new ProductNotFoundException(idproduct, e);
        } catch (Exception e) {
            // Throw a TechnicalException for any other exception
            throw new TechnicalException(e);
        }
        return productDTO;
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        // Convert the ProductDTO to a Product entity using ModelMapper
        Product productEntity = modelMapper.map(product, Product.class);

        if (product.getIdproduct() == null) {
            // If the product ID is null, it is a new product
            List<String> ids1 = new ArrayList<String>();
            for (ImageDTO image : product.getImages()) {
                ids1.add(image.getIdimage());
            }
            // Retrieve the images by their IDs and set them in the product entity
            productEntity.setImages(imageRepository.findByImages(ids1));

            List<String> ids2 = new ArrayList<String>();
            for (TagDTO tag : product.getTags()) {
                ids2.add(tag.getIdtag());
            }
            // Retrieve the tags by their IDs and set them in the product entity
            productEntity.setTags(tagRepository.findByTags(ids2));
        } else {
            // If the product ID is not null, it is an existing product
            // Retrieve the existing product entity by its ID
            productEntity = productRepository.findByIdproduct(product.getIdproduct());
            // Update the existing product entity with the values from the ProductDTO
            productEntity = modelMapper.map(product, Product.class);
        }
        // Save the product entity in the database
        productEntity = productRepository.save(productEntity);
        // Convert the saved product entity to a ProductDTO using ModelMapper
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    @Override
    @Transactional
    public void deleteProduct(String idproduct) {
        try {
            // Retrieve the product entity by its ID
            Product productEntity = productRepository.findByIdproduct(idproduct);
            // Remove the associations with users, images, and tags
            productEntity.setUsers(null);
            productEntity.setImages(null);
            productEntity.setTags(null);
            // Save the updated product entity
            productRepository.save(productEntity);
            // Delete the product entity from the database
            productRepository.delete(productEntity);
        } catch (EmptyResultDataAccessException e) {
            // Throw a ProductNotFoundException if the product is not found
            throw new ProductNotFoundException(idproduct, e);
        } catch (Exception e) {
            // Throw a TechnicalException for any other exception
            throw new TechnicalException(e);
        }
    }

}
