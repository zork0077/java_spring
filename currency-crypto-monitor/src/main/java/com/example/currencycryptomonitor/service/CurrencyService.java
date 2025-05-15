package com.example.currencycryptomonitor.service;

import com.example.currencycryptomonitor.model.CurrencyRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    private final String apiUrl = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    public CurrencyRate[] getCurrencyRates() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, CurrencyRate[].class);
    }
}
