package com.klasha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasha.dto.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetTopCities() throws Exception {
        // Define the request parameters
        String N = "10";
        // Perform the GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/country-info/most-populated-cities")
                        .param("N", N)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Convert the response JSON to your ApiResponse class
        String content = result.getResponse().getContentAsString();
        ApiResponse<Map<String, List<String>>, String> response = objectMapper.readValue(content, ApiResponse.class);

        assertEquals(200, response.getStatus());
        assertNotNull(response.getData());
    }
    @Test
    public void testGetCountryDetails() throws Exception {
        String countryName = "Italy";

        // Perform the GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/country-info/country-info")
                        .param("countryName", countryName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Convert the response JSON to your ApiResponse class
        String content = result.getResponse().getContentAsString();
        ApiResponse<Map<String, Object>, String> response = objectMapper.readValue(content, ApiResponse.class);

        assertEquals(200, response.getStatus());
        assertNotNull(response.getData());
    }
    @Test
    public void testGetStateAndCities() throws Exception {

        String countryName = "Italy";

        // Perform the GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/country-info/states-and-cities")
                        .param("countryName", countryName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Convert the response JSON to your ApiResponse class
        String content = result.getResponse().getContentAsString();
        ApiResponse<Map<String, List<String>>, String> response = objectMapper.readValue(content, ApiResponse.class);

        assertEquals(200, response.getStatus());
        assertNotNull(response.getData());
    }
    @Test
    void testConvertAmount() throws Exception {

        String countryName = "Italy";
        String monetaryAmount = "100";
        String targetCurrency = "NGN";

        // Perform the GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/country-info/currency-conversion")
                        .param("countryName", countryName)
                        .param("monetaryAmount", monetaryAmount)
                        .param("targetCurrency", targetCurrency)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Convert the response JSON to your ApiResponse class
        String content = result.getResponse().getContentAsString();
        ApiResponse<Map<String, String>, String> response = objectMapper.readValue(content, ApiResponse.class);

        assertEquals(200, response.getStatus());
        assertNotNull(response.getData());
    }
}