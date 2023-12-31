package com.klasha.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate(clientHttpRequestFactory());
}

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}
