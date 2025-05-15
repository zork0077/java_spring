package com.example.currencycryptomonitor.controller;

import com.example.currencycryptomonitor.model.CurrencyRate;
import com.example.currencycryptomonitor.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public CurrencyRate[] getRates() {
        return currencyService.getCurrencyRates();
    }
}
