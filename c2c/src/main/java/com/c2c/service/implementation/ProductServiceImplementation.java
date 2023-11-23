package com.c2c.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.c2c.dto.ImageDTO;
import com.c2c.dto.ProductDTO;
import com.c2c.dto.TagDTO;
import com.c2c.model.Product;
import com.c2c.repository.ImageRepository;
import com.c2c.repository.ProductRepository;
import com.c2c.repository.TagRepository;
import com.c2c.service.ProductService;
import com.c2c.service.exception.ProductNotFoundException;
import com.c2c.service.exception.TechnicalException;

@Service
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
        List<Product> listProductsEntity = productRepository.findAll();
        List<ProductDTO> listProductDTO = listProductsEntity.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return listProductDTO;
    }

    @Override
    public ProductDTO getProductById(String idproduct) {
        ProductDTO productDTO = null;
        try {
            Product productEntity = productRepository.findByIdproduct(idproduct);
            modelMapper.map(productEntity, ProductDTO.class);
        } catch (EntityNotFoundException e) {
            throw new ProductNotFoundException(idproduct, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        return productDTO;
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product productEntity = modelMapper.map(product, Product.class);

        if (product.getIdproduct() == null) {
            List<String> ids1 = new ArrayList<String>();
            for (ImageDTO image : product.getImages()) {
                ids1.add(image.getIdimage());
            }
            productEntity.setImages(imageRepository.findByImages(ids1));

            List<String> ids2 = new ArrayList<String>();
            for (TagDTO tag : product.getTags()) {
                ids2.add(tag.getIdtag());
            }
            productEntity.setTags(tagRepository.findByTags(ids2));
        } else {
            productEntity = productRepository.findByIdproduct(product.getIdproduct());
            productEntity = modelMapper.map(product, Product.class);
        }
        productEntity = productRepository.save(productEntity);
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    @Override
    public void deleteProduct(String idproduct) {
        try {
            productRepository.deleteById(idproduct);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException(idproduct, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }

}
