package com.klasha.service.impl;

import com.klasha.constants.AppConstants;
import com.klasha.dto.request.ExchangeRate;
import com.klasha.dto.request.PopulationFilterRequest;
import com.klasha.dto.response.*;
import com.klasha.exception.ApplicationException;
import com.klasha.service.CountryInfoService;
import com.klasha.service.RestClient;
import com.klasha.utils.CsvReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.klasha.constants.AppConstants.*;
import static com.klasha.constants.AppConstants.EXCEPTION;

@RequiredArgsConstructor
@Service
@Slf4j
public class CountryInfoServiceImpl implements CountryInfoService {
    private final RestClient restClient;
    private final AppConstants appConstants;

    @Override
    public Map<String, List<String>> getTopCitiesByPopulation(String numberOfCities) {
        log.info("Requested number of cities: {}", numberOfCities);
        Map<String, List<String>> populationResponse = new HashMap<>();

        //Get italy top N cites by population
        List<String> italyPopulationRes = populateCityData(ITALY, numberOfCities);
        populationResponse.put(ITALY,italyPopulationRes);

        //Get New Zealand top N cites by population
        List<String> newZealandPopulationRes = populateCityData(NEW_ZEALAND, numberOfCities);
        populationResponse.put(NEW_ZEALAND,newZealandPopulationRes);

        //Get Ghana top N cites by population
        List<String> ghanaPopulationRes = populateCityData(GHANA, numberOfCities);
        populationResponse.put(GHANA,ghanaPopulationRes);

        return populationResponse;
    }

    private List<String> populateCityData(String country, String numberOfCities) {
        try {
            PopulationFilterRequest populationRequest = buildPopulationRequest(country);
            PopulationFilterResponse populationResponse = restClient.doPost(PopulationFilterResponse.class,
                    populationRequest, appConstants.getPopulationFilterUrl());
            log.info("city data: {}", populationResponse);
            return getData(populationResponse, numberOfCities);
        } catch (Exception e) {
            log.error(EXCEPTION + e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }
    private List<String> getData(PopulationFilterResponse populationResponse, String numberOfCities) {
        return populationResponse.getData().size() < Integer.parseInt(numberOfCities) ?
                populationResponse.getData()
                        .stream().map(PopulationFilterResponse.DataDto::getCity).collect(Collectors.toList())
                : populationResponse.getData().subList(0, Integer.parseInt(numberOfCities))
                .stream().map(PopulationFilterResponse.DataDto::getCity).collect(Collectors.toList());
    }

    private PopulationFilterRequest buildPopulationRequest(String country) {
        return PopulationFilterRequest.builder()
                .country(country)
                .orderBy(POPULATION)
                .order(DESCENDING)
                .build();
    }

    @Override
    public Map<String, Object> getCountryDetails(String countryName) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            responseMap.put("population", getPopulation(countryName));
            responseMap.putAll(getCapitalAndLocationDetails(countryName));
            responseMap.putAll(getCurrencyAndIsoDetails(countryName));

            return responseMap;
        } catch (Exception e) {
            log.error( EXCEPTION + e.getMessage(),e);
            throw new ApplicationException(e.getMessage());
        }
    }

    private Long getPopulation(String countryName) {
        PopulationResponse populationResponse = restClient.doPost(PopulationResponse.class,
                Collections.singletonMap(COUNTRY, countryName), appConstants.getPopulationUrl());

        return populationResponse.getData().getPopulationCounts()
                .stream()
                .max(Comparator.comparingInt(PopulationResponse.Data.PopulationCount::getYear))
                .map(PopulationResponse.Data.PopulationCount::getValue)
                .orElse(null);
    }

    private Map<String, Object> getCapitalAndLocationDetails(String countryName) {
        Map<String, Object> detailsMap = new HashMap<>();
        try {
            Map<String, String> request = Collections.singletonMap(COUNTRY, countryName);

            CapitalCityResponse capitalCityResponse = restClient.doPost(CapitalCityResponse.class,
                    request, appConstants.getCapitalCityUrl());
            String capital = capitalCityResponse.getData().getCapital();
            detailsMap.put("capital", capital);

            LocationResponse locationResponse = restClient.doPost(LocationResponse.class,
                    request, appConstants.getLocationUrl());
            Map<String, Integer> locationMap = new HashMap<>();
            locationMap.put("longitude", locationResponse.getData().getLongitude());
            locationMap.put("latitude", locationResponse.getData().getLat());
            detailsMap.put("location", locationMap);

            return detailsMap;
        } catch (Exception e) {
            log.error(EXCEPTION + e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    private Map<String, Object> getCurrencyAndIsoDetails(String countryName) {
        Map<String, Object> detailsMap = new HashMap<>();
        try {
            Map<String, String> request = Collections.singletonMap("country", countryName);

            CurrencyResponse currencyResponse = restClient.doPost(CurrencyResponse.class,
                    request, appConstants.getCurrencyUrl());
            String currency = currencyResponse.getData().getCurrency();
            detailsMap.put("currency", currency);

            IsoResponse isoResponse = restClient.doPost(IsoResponse.class,
                    request, appConstants.getIsoUrl());
            detailsMap.put("iso2", isoResponse.getData().getIso2());
            detailsMap.put("iso3", isoResponse.getData().getIso3());

            return detailsMap;
        } catch (Exception e) {
            log.error(EXCEPTION + e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public Map<String, List<String>> getStateAndCities(String countryName) {
        Map<String, List<String>> stateAndCities = new HashMap<>();
        try {
            Map<String,String> request = new HashMap<>();
            request.put(COUNTRY,countryName);
            StateResponse stateResponse = restClient.doPost(StateResponse.class, request, appConstants.getStateUrl());

            stateResponse.getData().getStates().forEach(state -> {
                log.info("state" +state);
                request.put("state", state.getName());
                CityResponse cityResponse = restClient.doPost(CityResponse.class, request, appConstants.getCityUrl());
                log.info("city" +cityResponse.getData());
                stateAndCities.put(state.getName(), cityResponse.getData());
            });

            return stateAndCities;
        } catch (Exception e) {
            log.error(EXCEPTION +  e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public Map<String, String> convertAmount(String countryName, String monetaryAmount, String targetCurrency) {
        Map<String, String> conversionResult = new HashMap<>();
        try {
            Map<String, String> request = Collections.singletonMap("country", countryName);
            CurrencyResponse currencyResponse = restClient.doPost(CurrencyResponse.class,
                    request, appConstants.getCurrencyUrl());
            String currency = currencyResponse.getData().getCurrency();
            conversionResult.put("currency", currency);

            List<ExchangeRate> exchangeRates = CsvReader.readCSV(EXCHANGE_RATE_FILE_NAME);
            OptionalDouble exchangeRateOptional = exchangeRates.stream()
                    .filter(rate -> rate.getTargetCurrency().equalsIgnoreCase(targetCurrency) &&
                            rate.getSourceCurrency().equalsIgnoreCase(currency))
                    .mapToDouble(ExchangeRate::getRate)
                    .findFirst();

            if (exchangeRateOptional.isEmpty()) {
                throw new ApplicationException("Exchange rate not found for the specified currencies.");
            }

            double exchangeRate = exchangeRateOptional.getAsDouble();
            double totalAmount = Double.parseDouble(monetaryAmount) * exchangeRate;

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            Locale defaultLocale = Locale.getDefault();
            String symbol = Currency.getInstance(targetCurrency).getSymbol(defaultLocale);
            String formattedAmount = symbol + " " + decimalFormat.format(totalAmount);

            conversionResult.put("amount", formattedAmount);

            return conversionResult;
        } catch (Exception e) {
            log.error(EXCEPTION + e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }
}
