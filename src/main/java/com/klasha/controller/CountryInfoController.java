package com.klasha.controller;

import com.klasha.dto.response.ApiResponse;
import com.klasha.service.CountryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/country-info")
public class CountryInfoController {
    private final CountryInfoService countryService;


    /**
     * Retrieves the top N most populated cities from Ghana, New Zealand, and Italy.
     *
     * @param N The number of cities to retrieve.
     * @return A map containing the top cities from each country.
     */
    @GetMapping("/most-populated-cities")
    public ResponseEntity<ApiResponse<Map<String, List<String>>,String>> getTopCities(@RequestParam String N) {
        return ResponseEntity.ok(ApiResponse.success(countryService.getTopCitiesByPopulation(N)));
    }

    /**
     * Retrieves population, capital city, location, currency, ISO2&3 information for the specified country.
     *
     * @param countryName The name of the country.
     * @return Details of the specified country.
     */
    @GetMapping("/country-info")
    public ResponseEntity<ApiResponse<Map<String, Object>,String>> getCountryDetails(@RequestParam String countryName) {
        return ResponseEntity.ok(ApiResponse.success(countryService.getCountryDetails(countryName)));
    }

    /**
     * Retrieves a list of cities in each state of the specified country.
     *
     * @param countryName The name of the country.
     * @return A map containing lists of cities in each state.
     */
    @GetMapping("/states-and-cities")
    public ResponseEntity<ApiResponse<Map<String, List<String>>,String>> getStateAndCities(@RequestParam String countryName) {
        return ResponseEntity.ok(ApiResponse.success(countryService.getStateAndCities(countryName)));
    }

    /**
     * Converts a monetary amount from the specified country's currency to the target currency.
     *
     * @param countryName      The name of the country.
     * @param monetaryAmount   The monetary amount to convert.
     * @param targetCurrency   The target currency code.
     * @return A map containing the converted amount and the target currency code.
     */
    @GetMapping("/currency-conversion")
    public ResponseEntity<ApiResponse<Map<String, String>,String>> convertAmount(@RequestParam String countryName,
                                                             @RequestParam String monetaryAmount,
                                                             @RequestParam String targetCurrency) {
        return ResponseEntity.ok(ApiResponse.success(countryService.convertAmount(countryName, monetaryAmount, targetCurrency)));
    }

}
