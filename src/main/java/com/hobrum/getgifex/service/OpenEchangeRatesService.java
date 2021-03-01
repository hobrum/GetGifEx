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


@Service
public class OpenEchangeRatesService {


    @Value("${openExBaseCurrencyCode}")
    private String openExBaseCurrencyCode;


    @Value("${openExcRatesApiToken}")
    private String openExcRatesApiToken;


    private IExchangeClient exchangeClient;

    public OpenEchangeRatesService(IExchangeClient exchangeClient) {

        this.exchangeClient = exchangeClient;

    }


    public ExchangeRates getLatest(){
        return exchangeClient.getExchange("latest", openExcRatesApiToken, openExBaseCurrencyCode.toUpperCase());
    }


    public ExchangeRates getYesterday(){
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = "historical/" +dateFormat.format(yesterdayCalendar.getTime());
        return exchangeClient.getExchange(yesterday, openExcRatesApiToken, openExBaseCurrencyCode.toUpperCase());
    }


    public boolean isExchangeRateIsHigher(String openExNeedCurrencyCode){
        openExNeedCurrencyCode = openExNeedCurrencyCode.toUpperCase();
        ExchangeRates today = getLatest();
        ExchangeRates yesterday = getYesterday();
        return today.getRates().get(openExNeedCurrencyCode) > yesterday.getRates().get(openExNeedCurrencyCode);
    }

}
