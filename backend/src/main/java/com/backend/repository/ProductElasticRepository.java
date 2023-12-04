package com.backend.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.ProductElastic;

@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<ProductElastic, String> {
    ProductElastic findByIdproduct(String idproduct);

    List<ProductElastic> findByUser(String user);

    @Query("{\"nested\": {\"path\": \"tags\", \"query\": {\"terms\": {\"tags.tag_name\": ?0}}}}")
    List<ProductElastic> findByTagsIn(List<String> tags);
}