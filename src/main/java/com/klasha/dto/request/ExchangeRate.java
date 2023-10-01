package com.klasha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {
    private String sourceCurrency;
    private String targetCurrency;
    private double rate;
}