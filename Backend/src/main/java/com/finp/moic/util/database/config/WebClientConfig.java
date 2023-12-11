package com.finp.moic.util.database.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${CHAT_APIURL}")
    private String apiUrl;

    @Value("${CHAT_APIKEY}")
    private String apiKey;

    @Value("${CHAT_MODEL}")
    private String model;

    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION,apiKey)
                .build();

        return webClient;
    }

}
