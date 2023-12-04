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
            productDTO = modelMapper.map(productEntity, ProductDTO.class);
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
    @Transactional
    public void deleteProduct(String idproduct) {
        try {
            Product productEntity = productRepository.findByIdproduct(idproduct);
            productEntity.setUsers(null);
            productEntity.setImages(null);
            productEntity.setTags(null);
            productRepository.save(productEntity);
            productRepository.delete(productEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException(idproduct, e);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }

}
