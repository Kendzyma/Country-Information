package com.klasha.service.impl;

import com.klasha.exception.ApplicationException;
import com.klasha.service.RestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestClientImpl implements RestClient {
    private final RestTemplate restTemplate;


    @Override
    public <T, U> U doPost(Class<U> clazz, T request, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<U> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, clazz);
            return exchange.getBody();
        }
        catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
    }
}
