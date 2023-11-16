package com.c2c.service;

import java.io.IOException;
import java.util.List;

import com.c2c.model.ProductElastic;

public interface ProductElasticService {
    public List<ProductElastic> getAllProductsElastic() throws IOException;

    public ProductElastic getProductElasticById(String idproduct) throws IOException;

    public ProductElastic createProductElastic(ProductElastic newProduct) throws IOException;

    public ProductElastic createOrUpdateProductElastic(String idproduct, ProductElastic newProduct) throws IOException;

    public ProductElastic updateProductElastic(String idproduct, ProductElastic newProduct) throws IOException;

    public ProductElastic deleteProductElastic(String idproduct) throws IOException;
}
