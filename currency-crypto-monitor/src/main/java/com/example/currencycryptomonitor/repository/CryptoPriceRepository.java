package com.example.currencycryptomonitor.repository;

import com.example.currencycryptomonitor.model.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {}
