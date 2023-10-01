package com.klasha.service;

import java.util.List;
import java.util.Map;

public interface CountryInfoService {
    Map<String, List<String>> getTopCitiesByPopulation(String numberOfCities);

    Map<String,Object> getCountryDetails(String countryName);

    Map<String,List<String>> getStateAndCities(String countryName);

    Map<String,String> convertAmount(String countryName, String monetaryAmount, String targetCurrency);
}