// package com.c2c;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;

// import com.c2c.model.ProductElastic;
// import com.c2c.model.TagElastic;
// import com.c2c.repository.ProductElasticRepository;

// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// @ActiveProfiles("test")
// class ElasticSearchServiceTest {

// @Autowired
// private ProductElasticRepository elasticSearchQuery;

// private ProductElastic testProduct;

// @BeforeEach
// public void setUp() {

// TagElastic tag1 = new TagElastic("tag1", "Bicicleta");
// TagElastic tag2 = new TagElastic("tag2", "Moto");
// TagElastic tag3 = new TagElastic("tag3", "Coche");
// TagElastic tag4 = new TagElastic("tag4", "Patinete");
// TagElastic tag5 = new TagElastic("tag5", "Monopatín");
// List<TagElastic> tags = List.of(tag1, tag2, tag3, tag4, tag5);
// testProduct = new ProductElastic("4", "testproduct4", "description112", 5.5,
// LocalDateTime.of(2023, 11, 13, 12, 30, 0), "place", tags, "1212");
// }

// @Test
// public void testCreateOrUpdateDocument() throws IOException {
// String result = elasticSearchQuery.createOrUpdateDocument(testProduct);
// assertTrue(result.equals("Document has been successfully created.")
// || result.equals("Document has been successfully updated."));
// }

// @Test
// public void testGetDocumentById() throws IOException {
// elasticSearchQuery.createOrUpdateDocument(testProduct);

// ProductElastic resultProduct =
// elasticSearchQuery.getDocumentById(testProduct.getIdproduct());
// assertEquals(testProduct.getName(), resultProduct.getName());
// assertEquals(testProduct.getDescription(), resultProduct.getDescription());
// assertEquals(testProduct.getPrice(), resultProduct.getPrice());
// assertEquals(testProduct.getDate(), resultProduct.getDate());
// assertEquals(testProduct.getPlace(), resultProduct.getPlace());
// assertEquals(testProduct.getTags(), resultProduct.getTags());
// assertEquals(testProduct.getUser(), resultProduct.getUser());
// }

// @Test
// public void testDeleteDocumentById() throws IOException {
// elasticSearchQuery.createOrUpdateDocument(testProduct);

// String result =
// elasticSearchQuery.deleteDocumentById(testProduct.getIdproduct());
// assertEquals("Product with id " + testProduct.getIdproduct() + " has been
// deleted.", result);
// }

// @Test
// public void testSearchAllDocuments() throws IOException, InterruptedException
// {
// elasticSearchQuery.createOrUpdateDocument(testProduct);

// Thread.sleep(1000);

// List<ProductElastic> products = elasticSearchQuery.searchAllDocuments();
// assertTrue(products.stream().anyMatch(p ->
// p.getIdproduct().equals(testProduct.getIdproduct())));
// }
// }
