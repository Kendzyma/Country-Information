package com.klasha.service;

public interface RestClient {
    <T,U> U doPost(Class<U> clazz, T request, String url);
}
