package com.klasha.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConstants {

    @Value("${com.klasha.population-url}")
    private String populationUrl;
    @Value("${com.klasha.population-filter-url}")
    private String populationFilterUrl;
    @Value("${com.klasha.capital-city-url}")
    private String capitalCityUrl;
    @Value("${com.klasha.location-url}")
    private String locationUrl;
    @Value("${com.klasha.currency-url}")
    private String currencyUrl;
    @Value("${com.klasha.iso-url}")
    private String isoUrl;
    @Value("${com.klasha.state-url}")
    private String stateUrl;
    @Value("${com.klasha.city-url}")
    private String cityUrl;
    public static final String APPLICATION_JSON = "application/json";
    public static final String DESCENDING = "dsc";
    public static final String ITALY = "Italy";
    public static final String NEW_ZEALAND = "New Zealand";
    public static final String GHANA = "Ghana";
    public static final String POPULATION = "populationCounts";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAILURE_MESSAGE = "failure";
    public static final String EXCHANGE_RATE_FILE_NAME = "exchange_rate.csv";
    public static final String COUNTRY= "country";
    public static final String EXCEPTION = "Exception: ";
}
