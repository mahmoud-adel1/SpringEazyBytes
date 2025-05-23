package com.eazybytes.eazyschool.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthenticationInterceptor() {
        return new BasicAuthRequestInterceptor("admin@eazyschool.com","admin");
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder
                .basicAuthentication("admin@eazyschool.com","admin")
                .build();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(ExchangeFilterFunctions
                        .basicAuthentication("admin@eazyschool.com","admin"))
                .build();
    }
}
