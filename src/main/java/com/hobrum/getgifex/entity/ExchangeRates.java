package com.hobrum.getgifex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates {


    private String base;


    private Map<String, Double> rates;

    public ExchangeRates() {

    }

    public Map<String, Double> getRates() {
        return rates;
    }

}
