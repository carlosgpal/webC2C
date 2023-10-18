package com.c2c;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.c2c.elasticSearch.ElasticSearchQuery;
import com.c2c.elasticSearch.Product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ElasticSearchQueryTest {
     @InjectMocks
    private ElasticSearchQuery elasticSearchQuery;

    @Mock
    private ElasticsearchClient client; // Assuming this is a dependency in ElasticSearchQuery that interacts with Elasticsearch.

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrUpdateDocument() throws IOException {
        Product product = new Product("12", "TestProduct", "This is a test product.", 1.2, new Date(123), "TestPlace", "tag1", "tag2", "tag3", "tag4", "tag5", "TestUser");
        String response = elasticSearchQuery.createOrUpdateDocument(product);
        assertEquals("Document created/updated successfully", response);
    }

    @Test
    public void testGetDocumentById() throws IOException {
        Product expectedProduct = new Product("12", "TestProduct", "This is a test product.", 1.2, new Date(123), "TestPlace", "tag1", "tag2", "tag3", "tag4", "tag5", "TestUser");
        Product actualProduct = elasticSearchQuery.getDocumentById("1");
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void testDeleteDocumentById() throws IOException {
        String response = elasticSearchQuery.deleteDocumentById("12");
        assertEquals("Document deleted successfully", response);
    }

    @Test
    public void testSearchAllDocuments() throws IOException {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product("12", "TestProduct", "This is a test product.", 1.2, new Date(123), "TestPlace", "tag1", "tag2", "tag3", "tag4", "tag5", "TestUser"));
        List<Product> actualProducts = elasticSearchQuery.searchAllDocuments();
        assertEquals(expectedProducts, actualProducts);
    }
}