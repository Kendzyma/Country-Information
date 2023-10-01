package com.klasha.utils;

import com.klasha.dto.request.ExchangeRate;
import com.klasha.exception.ApplicationException;
import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public static List<ExchangeRate> readCSV(String filePath ) {
        ClassPathResource resource = new ClassPathResource(filePath);
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            reader.skip(1);
            return reader.readAll().stream()
                    .map(CsvReader::mapToExchangeRate)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
    }

    private static ExchangeRate mapToExchangeRate(String[] row) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setSourceCurrency(row[0]);
        exchangeRate.setTargetCurrency(row[1]);
        exchangeRate.setRate(Double.parseDouble(row[2]));
        return exchangeRate;
    }
}
