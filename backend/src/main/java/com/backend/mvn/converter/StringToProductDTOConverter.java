package com.backend.mvn.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.backend.dto.ProductDTO;
import com.backend.service.ProductService;

@Component
public class StringToProductDTOConverter implements Converter<String, ProductDTO> {
    @Autowired
    ProductService productService;

    @Override
    public ProductDTO convert(String id) {
        ProductDTO productDTO = productService.getProductById(id);
        return productDTO;
    }

}
