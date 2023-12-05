package com.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.backend.model.ProductElastic;
import com.backend.model.TagElastic;
import com.backend.repository.ProductElasticRepository;
import com.backend.service.ProductElasticService;

import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductElasticSearchServiceTest {

    @Autowired
    private ProductElasticService productElasticService;

    @Autowired
    private ProductElasticRepository productElasticRepository;

    // Define some static tags
    private static TagElastic tag1 = new TagElastic("tag1", "Bicicleta");
    private static TagElastic tag2 = new TagElastic("tag2", "Moto");
    private static TagElastic tag3 = new TagElastic("tag3", "Coche");
    private static TagElastic tag4 = new TagElastic("tag4", "Patinete");
    private static TagElastic tag5 = new TagElastic("tag5", "Monopatín");

    // Define some static test products
    private static ProductElastic testProduct1 = new ProductElastic("1",
            "testproduct1", "description112", 5.5,
            new Date(123), "place", List.of(tag1, tag4,
                    tag5),
            "1212");
    private static ProductElastic testProduct2 = new ProductElastic("2",
            "testproduct2", "description112", 5.5,
            new Date(123), "place", List.of(tag3, tag4,
                    tag5),
            "1212");
    private static ProductElastic testProduct3 = new ProductElastic("3",
            "testproduct3", "description112", 5.5,
            new Date(123), "place", List.of(tag1, tag2),
            "1212");
    private static ProductElastic testProduct4 = new ProductElastic("4",
            "testproduct4", "description112", 5.5,
            new Date(123), "place", List.of(tag1, tag2, tag3,
                    tag4, tag5),
            "1213");

    @BeforeEach
    public void setUp() throws IOException {
        // Delete all existing products
        productElasticRepository.deleteAll();

        // Create test products
        productElasticService.createProductElastic(testProduct1);
        productElasticService.createProductElastic(testProduct2);
        productElasticService.createProductElastic(testProduct3);
        productElasticService.createProductElastic(testProduct4);
    }

    @Test
    public void testGetAllProductsElastic() throws IOException {
        // Test getting all products
        List<ProductElastic> products = productElasticService.getAllProductsElastic();
        assertEquals(4, products.size());

        // Test getting all products when there are no products
        productElasticRepository.deleteAll();
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getAllProductsElastic();
        });
        String expectedMessage = "No productsElastic found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testGetProductElasticById() throws IOException {
        // Test getting a product by ID
        ProductElastic product = productElasticService.getProductElasticById(testProduct1.getIdproduct());
        assertEquals(testProduct1.getIdproduct(), product.getIdproduct());

        // Test getting a non-existing product by ID
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getProductElasticById("non-existing-id");
        });
        String expectedMessage = "ProductElastic with ID: non-existing-id not found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateProductElastic() throws IOException {
        // Test creating a new product
        ProductElastic newProduct = new ProductElastic("5", "testproduct5",
                "description112", 5.5,
                new Date(123), "place", List.of(tag1, tag2, tag3,
                        tag4, tag5),
                "1212");
        ProductElastic createdProduct = productElasticService.createProductElastic(newProduct);
        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getIdproduct());

        // Test creating a product with an existing ID
        assertTrue(productElasticRepository.findById(createdProduct.getIdproduct()).isPresent());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productElasticService.createProductElastic(createdProduct);
        });
        String expectedMessage = "ProductElastic with ID: " + createdProduct.getIdproduct()
                + " already exists. Use the update method instead.";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateOrUpdateProductElastic() throws IOException {
        // Test updating an existing product
        testProduct1.setName("Updated Name");
        ProductElastic updatedProduct = productElasticService.createOrUpdateProductElastic(testProduct1.getIdproduct(),
                testProduct1);
        assertEquals("Updated Name", updatedProduct.getName());

        // Test creating a new product
        ProductElastic newProduct = new ProductElastic("6", "testproduct6",
                "description112", 5.5,
                new Date(123), "place", List.of(tag1, tag2, tag3,
                        tag4, tag5),
                "1212");
        ProductElastic createdProduct = productElasticService.createOrUpdateProductElastic("2", newProduct);
        assertNotNull(createdProduct.getIdproduct());
    }

    @Test
    public void testUpdateProductElastic() throws IOException {
        // Test updating an existing product
        testProduct1.setName("Updated Name");
        ProductElastic updatedProduct = productElasticService.updateProductElastic(testProduct1.getIdproduct(),
                testProduct1);
        assertEquals("Updated Name", updatedProduct.getName());

        // Test updating a non-existing product
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.updateProductElastic("non-existing-id", testProduct1);
        });
        String expectedMessage = "ProductElastic with ID: non-existing-id not found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testDeleteProductElastic() throws IOException {
        // Test deleting an existing product
        ProductElastic deletedProduct = productElasticService.deleteProductElastic(testProduct1.getIdproduct());
        assertEquals(testProduct1.getIdproduct(), deletedProduct.getIdproduct());

        // Test deleting a non-existing product
        assertFalse(productElasticRepository.findById(testProduct1.getIdproduct()).isPresent());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.deleteProductElastic("non-existing-id");
        });
        String expectedMessage = "ProductElastic with ID: non-existing-id not found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testGetProductsElasticByUser() throws IOException {
        // Test getting products by user ID
        List<ProductElastic> productsByUser = productElasticService.getProductsElasticByUser("1212");
        assertEquals(3, productsByUser.size());

        // Test getting products by non-existing user ID
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getProductsElasticByUser("non-existing-id");
        });
        String expectedMessage = "No productsElastic found for user with ID: non-existing-id";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testGetProductsElasticByTags() throws IOException {
        // Test getting products by tags
        List<ProductElastic> productsByTags = productElasticService.getProductsElasticByTags(List.of("tag1", "tag2"));
        assertEquals(3, productsByTags.size());

        // Test getting products by non-existing tag
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getProductsElasticByTags(List.of("non-existing-tag"));
        });
        String expectedMessage = "No productsElastic found for tags: [non-existing-tag]";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
