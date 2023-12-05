package com.backend.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.ProductElastic;

// This is the repository for the ProductElastic model of the elasticsearch database
@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<ProductElastic, String> {
    ProductElastic findByIdproduct(String idproduct);

    List<ProductElastic> findByUser(String user);

    // This is a custom query that returns a list of products that match the ids of
    // the tags passed
    @Query("{\"nested\": {\"path\": \"tags\", \"query\": {\"terms\": {\"tags.tag_name\": ?0}}}}")
    List<ProductElastic> findByTagsIn(List<String> tags);
}