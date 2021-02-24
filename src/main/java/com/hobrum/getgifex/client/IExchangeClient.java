package com.hobrum.getgifex.client;

import com.hobrum.getgifex.entity.ExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange", url = "${openExRatesApiURL}")
public interface IExchangeClient {

    @RequestMapping(value = "/api/{date}.json", params = {"app_id", "base"}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRates getExchange(@PathVariable String date, @RequestParam(value = "app_id") String appId, @RequestParam(value = "base") String base);

}
