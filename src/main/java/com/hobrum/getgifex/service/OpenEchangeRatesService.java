package com.hobrum.getgifex.service;

import com.hobrum.getgifex.client.IExchangeClient;
import com.hobrum.getgifex.entity.ExchangeRates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootTest
@Service
public class OpenEchangeRatesService {

    @MockBean
    @Value("${openExBaseCurrencyCode}")
    private String openExBaseCurrencyCode;

    @MockBean
    @Value("${openExcRatesApiToken}")
    private String openExcRatesApiToken;

    @MockBean
    private IExchangeClient exchangeClient;

    public OpenEchangeRatesService(IExchangeClient exchangeClient) {

        this.exchangeClient = exchangeClient;

    }

    @Test
    public ExchangeRates getToday(){
        return exchangeClient.getExchange("latest", openExcRatesApiToken, openExBaseCurrencyCode.toUpperCase());
    }

    @Test
    public ExchangeRates getYesterday(){
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = "historical/" +dateFormat.format(yesterdayCalendar.getTime());
        return exchangeClient.getExchange(yesterday, openExcRatesApiToken, openExBaseCurrencyCode.toUpperCase());
    }

    @Test
    public boolean isExchangeRateIsHigher(String openExNeedCurrencyCode){
        openExNeedCurrencyCode = openExNeedCurrencyCode.toUpperCase();
        ExchangeRates today = getToday();
        ExchangeRates yesterday = getYesterday();
        return today.getRates().get(openExNeedCurrencyCode) > yesterday.getRates().get(openExNeedCurrencyCode);
    }

}
