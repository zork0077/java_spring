package com.example.currencycryptomonitor.controller;

import com.example.currencycryptomonitor.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CryptoViewController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/")
    public String index() {
        return "index"; // Thymeleaf шаблон index.html
    }

    @GetMapping("/crypto")
    public String getPrice(@RequestParam String from, @RequestParam String to, Model model) {
        String price = cryptoService.getCryptoPrice(from, to);
        model.addAttribute("price", price);
        return "index";
    }
}
