package com.example.currencycryptomonitor.controller;

import com.example.currencycryptomonitor.service.CryptoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/{from}/{to}")
    public String getCryptoPrice(@PathVariable String from, @PathVariable String to) {
        return cryptoService.getCryptoPrice(from, to);
    }

    // 🔽 Ось сюди додай метод для експорту в Excel
    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) {
        cryptoService.exportToExcel(response);
    }
}
