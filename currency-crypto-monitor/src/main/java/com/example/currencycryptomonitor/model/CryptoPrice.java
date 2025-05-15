package com.example.currencycryptomonitor.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class CryptoPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cryptoFrom;
    private String currencyTo;
    private BigDecimal price;

    private LocalDateTime timestamp = LocalDateTime.now();

    // геттери і сеттери
    public Long getId() { return id; }

    public String getCryptoFrom() { return cryptoFrom; }
    public void setCryptoFrom(String cryptoFrom) { this.cryptoFrom = cryptoFrom; }

    public String getCurrencyTo() { return currencyTo; }
    public void setCurrencyTo(String currencyTo) { this.currencyTo = currencyTo; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(double price) { this.price = BigDecimal.valueOf(price); }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
