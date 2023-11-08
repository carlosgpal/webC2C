package com.c2c;

import org.springframework.web.bind.annotation.RestController;

import com.c2c.elasticSearch.ElasticSearchQuery;
import com.c2c.elasticSearch.ProductElastic;
import com.c2c.model.Product;
import com.c2c.model.ProductInterface;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductInterface productIf;

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productIf.findAll().forEach(products::add);
        return ResponseEntity.ok(products);
    }

    // Get product by Id
    @GetMapping("/{idproduct}")
    public ResponseEntity<Product> getProductById(@PathVariable String idproduct) {
        Product product = productIf.findByIdproduct(idproduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Create a New Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) throws IOException {
        Product product = productIf.save(newProduct);

        ProductElastic elasticProduct = new ProductElastic();
        elasticProduct.setIdproduct(product.getIdproduct());
        elasticProduct.setName(product.getName());
        elasticProduct.setDescription(product.getDescription());
        elasticProduct.setPrice(product.getPrice());
        elasticProduct.setDate(product.getDate());
        elasticProduct.setPlace(product.getPlace());
        elasticProduct.setTag1(product.getTag1());
        elasticProduct.setTag2(product.getTag2());
        elasticProduct.setTag3(product.getTag3());
        elasticProduct.setTag4(product.getTag4());
        elasticProduct.setTag5(product.getTag5());
        elasticProduct.setUser(product.getUser_iduser().getIduser());

        elasticSearchQuery.createOrUpdateDocument(elasticProduct);

        return ResponseEntity.ok(product);
    }

    // Update a Product
    @PutMapping("/{idproduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable String idproduct, @RequestBody Product updatedProduct)
            throws IOException {
        Product product = productIf.findByIdproduct(idproduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setDate(updatedProduct.getDate());
        product.setPlace(updatedProduct.getPlace());
        product.setImage1(updatedProduct.getImage1());
        product.setImage2(updatedProduct.getImage2());
        product.setImage3(updatedProduct.getImage3());
        product.setImage4(updatedProduct.getImage4());
        product.setImage5(updatedProduct.getImage5());
        product.setTag1(updatedProduct.getTag1());
        product.setTag2(updatedProduct.getTag2());
        product.setTag3(updatedProduct.getTag3());
        product.setTag4(updatedProduct.getTag4());
        product.setTag5(updatedProduct.getTag5());
        product.setUser_iduser(updatedProduct.getUser_iduser());
        productIf.save(product);

        ProductElastic elasticProduct = new ProductElastic();
        elasticProduct.setIdproduct(idproduct);
        elasticProduct.setName(updatedProduct.getName());
        elasticProduct.setDescription(updatedProduct.getDescription());
        elasticProduct.setPrice(updatedProduct.getPrice());
        elasticProduct.setDate(updatedProduct.getDate());
        elasticProduct.setPlace(updatedProduct.getPlace());
        elasticProduct.setTag1(updatedProduct.getTag1());
        elasticProduct.setTag2(updatedProduct.getTag2());
        elasticProduct.setTag3(updatedProduct.getTag3());
        elasticProduct.setTag4(updatedProduct.getTag4());
        elasticProduct.setTag5(updatedProduct.getTag5());
        elasticProduct.setUser(updatedProduct.getUser_iduser().getIduser());

        elasticSearchQuery.createOrUpdateDocument(elasticProduct);

        return ResponseEntity.ok(product);
    }

    // Delete a Product
    @DeleteMapping("/{idproduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String idproduct) throws IOException {
        
        elasticSearchQuery.deleteDocumentById(idproduct);

        Product product = productIf.findByIdproduct(idproduct);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productIf.delete(product);

        return ResponseEntity.noContent().build();
    }
}
