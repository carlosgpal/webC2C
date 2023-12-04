package com.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.ProductElastic;
import com.backend.service.ProductElasticService;

@RestController
@RequestMapping("/api/elastic")
public class ElasticController {

    @Autowired
    private ProductElasticService productElasticService;

    @GetMapping
    public List<ProductElastic> getAllProducts() throws IOException {
        return productElasticService.getAllProductsElastic();
    }

    @PostMapping
    public ProductElastic saveProduct(@RequestBody ProductElastic newProduct) throws IOException {
        return productElasticService.createProductElastic(newProduct);
    }

}
