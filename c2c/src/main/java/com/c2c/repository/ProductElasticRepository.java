package com.c2c.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.c2c.model.ProductElastic;

@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<ProductElastic, String> {
    ProductElastic findByIdproduct(String idproduct);
}