// package com.c2c;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;

// import com.c2c.model.ProductElastic;
// import com.c2c.model.TagElastic;
// import com.c2c.repository.ProductElasticRepository;
// import com.c2c.service.ProductElasticService;

// import jakarta.persistence.EntityNotFoundException;

// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// @ActiveProfiles("test")
// class ElasticSearchServiceTest {

// @Autowired
// private ProductElasticService productElasticService;

// @Autowired
// private ProductElasticRepository productElasticRepository;

// private static TagElastic tag1 = new TagElastic("tag1", "Bicicleta");
// private static TagElastic tag2 = new TagElastic("tag2", "Moto");
// private static TagElastic tag3 = new TagElastic("tag3", "Coche");
// private static TagElastic tag4 = new TagElastic("tag4", "Patinete");
// private static TagElastic tag5 = new TagElastic("tag5", "Monopatín");

// private static ProductElastic testProduct1 = new ProductElastic("1",
// "testproduct1", "description112", 5.5,
// LocalDateTime.of(2023, 11, 13, 12, 30, 0), "place", List.of(tag1, tag4,
// tag5),
// "1212");
// private static ProductElastic testProduct2 = new ProductElastic("2",
// "testproduct2", "description112", 5.5,
// LocalDateTime.of(2023, 11, 13, 12, 30, 0), "place", List.of(tag3, tag4,
// tag5),
// "1212");
// private static ProductElastic testProduct3 = new ProductElastic("3",
// "testproduct3", "description112", 5.5,
// LocalDateTime.of(2023, 11, 13, 12, 30, 0), "place", List.of(tag1, tag2, tag3,
// tag5),
// "1212");
// private static ProductElastic testProduct4 = new ProductElastic("4",
// "testproduct4", "description112", 5.5,
// LocalDateTime.of(2023, 11, 13, 12, 30, 0), "place", List.of(tag1, tag2, tag3,
// tag4, tag5),
// "1212");

// @BeforeEach
// public void setUp() throws IOException {
// productElasticRepository.deleteAll();
// productElasticService.createProductElastic(testProduct1);
// productElasticService.createProductElastic(testProduct2);
// productElasticService.createProductElastic(testProduct3);
// productElasticService.createProductElastic(testProduct4);
// }

// @Test
// public void testGetAllProductsElastic() throws IOException {
// // Prueba para obtener todos los productos
// List<ProductElastic> products =
// productElasticService.getAllProductsElastic();
// assertEquals(2, products.size()); // Asegura que hay 2 productos

// // Prueba para la condición de error
// productElasticRepository.deleteAll();
// Exception exception = assertThrows(EntityNotFoundException.class, () -> {
// productElasticService.getAllProductsElastic();
// });
// String expectedMessage = "No productsElastic found";
// assertTrue(exception.getMessage().contains(expectedMessage));
// }

// @Test
// public void testGetProductElasticById() throws IOException {
// // Prueba para obtener un producto por ID
// ProductElastic product =
// productElasticService.getProductElasticById(testProduct1.getIdproduct());
// assertEquals(testProduct1.getIdproduct(), product.getIdproduct());

// // Prueba para la condición de error
// Exception exception = assertThrows(EntityNotFoundException.class, () -> {
// productElasticService.getProductElasticById("non-existing-id");
// });
// String expectedMessage = "ProductElastic with ID: non-existing-id not found";
// assertTrue(exception.getMessage().contains(expectedMessage));
// }

// @Test
// public void testCreateProductElastic() throws IOException {
// ProductElastic newProduct = new ProductElastic("5", "testproduct5",
// "description112", 5.5,
// LocalDateTime.of(2023, 11, 13, 12, 30, 0), "place", List.of(tag1, tag2, tag3,
// tag4, tag5),
// "1212");
// ProductElastic createdProduct =
// productElasticService.createProductElastic(newProduct);
// assertNotNull(createdProduct);
// assertNotNull(createdProduct.getIdproduct());

// // Verifica si el producto está realmente en el repositorio
// assertTrue(productElasticRepository.findById(createdProduct.getIdproduct()).isPresent());
// }

// @Test
// public void testCreateOrUpdateProductElastic() throws IOException {
// // Actualiza el producto existente
// testProduct1.setName("Updated Name");
// ProductElastic updatedProduct =
// productElasticService.createOrUpdateProductElastic(testProduct1.getIdproduct(),
// testProduct1);
// assertEquals("Updated Name", updatedProduct.getName());

// // Crea un nuevo producto
// ProductElastic newProduct = new ProductElastic("6", "testproduct6",
// "description112", 5.5,
// LocalDateTime.of(2023, 11, 13, 12, 30, 0), "place", List.of(tag1, tag2, tag3,
// tag4, tag5),
// "1212");
// ProductElastic createdProduct =
// productElasticService.createOrUpdateProductElastic(null, newProduct);
// assertNotNull(createdProduct.getIdproduct());
// }

// @Test
// public void testUpdateProductElastic() throws IOException {
// testProduct1.setName("Updated Name");
// ProductElastic updatedProduct =
// productElasticService.updateProductElastic(testProduct1.getIdproduct(),
// testProduct1);
// assertEquals("Updated Name", updatedProduct.getName());

// // Prueba para la condición de error
// Exception exception = assertThrows(EntityNotFoundException.class, () -> {
// productElasticService.updateProductElastic("non-existing-id", testProduct1);
// });
// String expectedMessage = "ProductElastic with ID: non-existing-id not found";
// assertTrue(exception.getMessage().contains(expectedMessage));
// }

// @Test
// public void testDeleteProductElastic() throws IOException {
// ProductElastic deletedProduct =
// productElasticService.deleteProductElastic(testProduct1.getIdproduct());
// assertEquals(testProduct1.getIdproduct(), deletedProduct.getIdproduct());

// // Verificar que el producto ya no está en el repositorio
// assertFalse(productElasticRepository.findById(testProduct1.getIdproduct()).isPresent());

// // Prueba para la condición de error
// Exception exception = assertThrows(EntityNotFoundException.class, () -> {
// productElasticService.deleteProductElastic("non-existing-id");
// });
// String expectedMessage = "ProductElastic with ID: non-existing-id not found";
// assertTrue(exception.getMessage().contains(expectedMessage));
// }

// @Test
// public void testGetProductsElasticByUser() throws IOException {
// List<ProductElastic> productsByUser =
// productElasticService.getProductsElasticByUser("noterminao");
// // Asegúrate de que la lista contiene los productos del usuario especificado
// }

// @Test
// public void testGetProductsElasticByTags() throws IOException {
// List<ProductElastic> productsByTags =
// productElasticService.getProductsElasticByTags(List.of("noterminao"));
// // Verifica que los productos contengan los tags especificados
// }

// }
