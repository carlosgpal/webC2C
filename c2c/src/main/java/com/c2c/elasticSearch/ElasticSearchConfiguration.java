package com.c2c.elasticSearch;

import com.c2c.config.ElasticsearchProperties;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    public ElasticSearchConfiguration(ElasticsearchProperties elasticsearchProperties) {
        this.elasticsearchProperties = elasticsearchProperties;
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder(
                new HttpHost(elasticsearchProperties.getHost(), elasticsearchProperties.getPort())
        ).build();
    }

    @Bean
    public ElasticsearchTransport elasticsearchTransport() {
        return new RestClientTransport(
                restClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        return new ElasticsearchClient(elasticsearchTransport());
    }
}
