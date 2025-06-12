    package com.example.dvdRentalApp.config;

    import co.elastic.clients.elasticsearch.ElasticsearchClient;
    import co.elastic.clients.json.jackson.JacksonJsonpMapper;
    import co.elastic.clients.transport.ElasticsearchTransport;
    import co.elastic.clients.transport.rest_client.RestClientTransport;
    import org.apache.http.HttpHost;
    import org.elasticsearch.client.RestClient;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;

    @Configuration
    public class ElasticsearchConfig {

        @Value("${spring.elasticsearch.uris}")
        private String elasticsearchUrl;

        @Bean
        public RestClient restClient() {
            return RestClient.builder(HttpHost.create(elasticsearchUrl)).build();
        }

        @Bean
        public ElasticsearchTransport elasticsearchTransport() {
            return new RestClientTransport(restClient(), new JacksonJsonpMapper());
        }

        @Bean
        public ElasticsearchClient elasticsearchClient() {
            return new ElasticsearchClient(elasticsearchTransport());
        }

    }

