// package com.c2c;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;

// import com.c2c.model.ProductElastic;
// import com.c2c.repository.ProductElasticRepository;

// import java.io.IOException;
// import java.util.Date;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// @ActiveProfiles("test")
// class ElasticSearchRepositoryTest {

//     @Autowired
//     private ProductElasticRepository elasticSearchQuery;


//     private ProductElastic testProduct;

//     @BeforeEach
//     public void setUp() {
//         testProduct = new ProductElastic("13", "testproduct13", "description112", 5.5, new Date(1122), "place", "tagprueba",
//                 "tagprueba", "tagprueba", "tagprueba", "tagprueba", "1212");
//     }

//     @Test
//     public void testCreateOrUpdateDocument() throws IOException {
//         String result = elasticSearchQuery.createOrUpdateDocument(testProduct);
//         assertTrue(result.equals("Document has been successfully created.")
//                 || result.equals("Document has been successfully updated."));
//     }

//     @Test
//     public void testGetDocumentById() throws IOException {
//         elasticSearchQuery.createOrUpdateDocument(testProduct);

//         ProductElastic resultProduct = elasticSearchQuery.getDocumentById(testProduct.getIdproduct());
//         assertEquals(testProduct.getName(), resultProduct.getName());
//         assertEquals(testProduct.getDescription(), resultProduct.getDescription());
//         assertEquals(testProduct.getPrice(), resultProduct.getPrice());
//         assertEquals(testProduct.getPlace(), resultProduct.getPlace());
//         assertEquals(testProduct.getTag1(), resultProduct.getTag1());
//         assertEquals(testProduct.getTag2(), resultProduct.getTag2());
//         assertEquals(testProduct.getTag3(), resultProduct.getTag3());
//         assertEquals(testProduct.getTag4(), resultProduct.getTag4());
//         assertEquals(testProduct.getTag5(), resultProduct.getTag5());
//         assertEquals(testProduct.getUser(), resultProduct.getUser());
//     }

//     @Test
//     public void testDeleteDocumentById() throws IOException {
//         elasticSearchQuery.createOrUpdateDocument(testProduct);

//         String result = elasticSearchQuery.deleteDocumentById(testProduct.getIdproduct());
//         assertEquals("Product with id " + testProduct.getIdproduct() + " has been deleted.", result);
//     }

//     @Test
//     public void testSearchAllDocuments() throws IOException {
//         elasticSearchQuery.createOrUpdateDocument(testProduct);

//         List<ProductElastic> products = elasticSearchQuery.searchAllDocuments();
//         assertTrue(products.stream().anyMatch(p -> p.getIdproduct().equals(testProduct.getIdproduct())));
//     }
// }
