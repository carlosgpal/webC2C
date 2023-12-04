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

    private static TagElastic tag1 = new TagElastic("tag1", "Bicicleta");
    private static TagElastic tag2 = new TagElastic("tag2", "Moto");
    private static TagElastic tag3 = new TagElastic("tag3", "Coche");
    private static TagElastic tag4 = new TagElastic("tag4", "Patinete");
    private static TagElastic tag5 = new TagElastic("tag5", "Monopatín");

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
        productElasticRepository.deleteAll();
        productElasticService.createProductElastic(testProduct1);
        productElasticService.createProductElastic(testProduct2);
        productElasticService.createProductElastic(testProduct3);
        productElasticService.createProductElastic(testProduct4);
    }

    @Test
    public void testGetAllProductsElastic() throws IOException {
        List<ProductElastic> products = productElasticService.getAllProductsElastic();
        assertEquals(4, products.size());

        productElasticRepository.deleteAll();
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getAllProductsElastic();
        });
        String expectedMessage = "No productsElastic found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testGetProductElasticById() throws IOException {
        ProductElastic product = productElasticService.getProductElasticById(testProduct1.getIdproduct());
        assertEquals(testProduct1.getIdproduct(), product.getIdproduct());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getProductElasticById("non-existing-id");
        });
        String expectedMessage = "ProductElastic with ID: non-existing-id not found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testCreateProductElastic() throws IOException {
        ProductElastic newProduct = new ProductElastic("5", "testproduct5",
                "description112", 5.5,
                new Date(123), "place", List.of(tag1, tag2, tag3,
                        tag4, tag5),
                "1212");
        ProductElastic createdProduct = productElasticService.createProductElastic(newProduct);
        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getIdproduct());

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
        testProduct1.setName("Updated Name");
        ProductElastic updatedProduct = productElasticService.createOrUpdateProductElastic(testProduct1.getIdproduct(),
                testProduct1);
        assertEquals("Updated Name", updatedProduct.getName());

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
        testProduct1.setName("Updated Name");
        ProductElastic updatedProduct = productElasticService.updateProductElastic(testProduct1.getIdproduct(),
                testProduct1);
        assertEquals("Updated Name", updatedProduct.getName());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.updateProductElastic("non-existing-id", testProduct1);
        });
        String expectedMessage = "ProductElastic with ID: non-existing-id not found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testDeleteProductElastic() throws IOException {
        ProductElastic deletedProduct = productElasticService.deleteProductElastic(testProduct1.getIdproduct());
        assertEquals(testProduct1.getIdproduct(), deletedProduct.getIdproduct());

        assertFalse(productElasticRepository.findById(testProduct1.getIdproduct()).isPresent());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.deleteProductElastic("non-existing-id");
        });
        String expectedMessage = "ProductElastic with ID: non-existing-id not found";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testGetProductsElasticByUser() throws IOException {
        List<ProductElastic> productsByUser = productElasticService.getProductsElasticByUser("1212");
        assertEquals(3, productsByUser.size());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getProductsElasticByUser("non-existing-id");
        });
        String expectedMessage = "No productsElastic found for user with ID: non-existing-id";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testGetProductsElasticByTags() throws IOException {
        List<ProductElastic> productsByTags = productElasticService.getProductsElasticByTags(List.of("tag1", "tag2"));
        assertEquals(3, productsByTags.size());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productElasticService.getProductsElasticByTags(List.of("non-existing-tag"));
        });
        String expectedMessage = "No productsElastic found for tags: [non-existing-tag]";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
