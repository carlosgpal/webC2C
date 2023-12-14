package com.backend.service;

import java.io.IOException;
import java.util.List;

import com.backend.model.ProductElastic;

// This is the port from the ProductElasticRepository to the ProductElasticController
public interface ProductElasticService {
    public List<ProductElastic> getAllProductsElastic() throws IOException;

    public ProductElastic getProductElasticById(String idproduct) throws IOException;

    public ProductElastic createProductElastic(ProductElastic newProduct) throws IOException;

    public ProductElastic createOrUpdateProductElastic(String idproduct, ProductElastic newProduct) throws IOException;

    public ProductElastic updateProductElastic(String idproduct, ProductElastic newProduct) throws IOException;

    public ProductElastic deleteProductElastic(String idproduct) throws IOException;

    public List<ProductElastic> getProductsElasticByUser(String iduser) throws IOException;

    public List<ProductElastic> getProductsElasticByTags(List<String> idtags) throws IOException;

    public List<ProductElastic> searchProductsByNameOrDescription(String query) throws IOException;
}
