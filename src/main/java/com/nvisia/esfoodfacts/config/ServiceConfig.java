package com.nvisia.esfoodfacts.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceConfig {

    private final ApplicationProperties applicationProperties;

    @Bean
    public RestClient restClient() {
        return RestClient.builder(
            new HttpHost(
                applicationProperties.getElasticsearch().getHost(),
                applicationProperties.getElasticsearch().getPort(),
        "http")).build();
    }

}
