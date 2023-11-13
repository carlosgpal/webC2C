package com.c2c.service.implementation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.c2c.model.ProductElastic;
import com.c2c.repository.ProductElasticRepository;
import com.c2c.service.ProductElasticService;

public class ProductElasticServiceImplementation implements ProductElasticService{

    @Autowired
    private ProductElasticRepository productElasticRepository;
    
    @Override
    public List<ProductElastic> getAllProductsElastic() throws IOException {
        return productElasticRepository.searchAllDocuments();
    }

    @Override
    public ProductElastic getProductElasticById(String idproduct) throws IOException {
        return productElasticRepository.getDocumentById(idproduct);
    }

    @Override
    public ProductElastic createProductElastic(ProductElastic newProduct) throws IOException {
        productElasticRepository.createOrUpdateDocument(newProduct);
        return newProduct;
    }

    @Override
    public ProductElastic updateProductElastic(String idproduct, ProductElastic newProduct) throws IOException {
        productElasticRepository.createOrUpdateDocument(newProduct);
        return newProduct;
    }

    @Override
    public ProductElastic deleteProductElastic(String idproduct) throws IOException {
        ProductElastic product = productElasticRepository.getDocumentById(idproduct);
        productElasticRepository.deleteDocumentById(idproduct);
        return product;
    }
    
}
