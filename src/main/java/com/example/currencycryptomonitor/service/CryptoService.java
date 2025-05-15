package com.example.currencycryptomonitor.service;

import com.example.currencycryptomonitor.model.CryptoPrice;
import com.example.currencycryptomonitor.repository.CryptoPriceRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
public class CryptoService {

    @Autowired
    private CryptoPriceRepository repository;

    public String getCryptoPrice(String from, String to) {
        try {
            String apiUrl = "https://api.coingecko.com/api/v3/simple/price?ids=" +
                    from.toLowerCase() +
                    "&vs_currencies=" +
                    to.toLowerCase();

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String result = scanner.hasNext() ? scanner.next() : "";

            JSONObject jsonObject = new JSONObject(result);
            double price = jsonObject.getJSONObject(from.toLowerCase()).getDouble(to.toLowerCase());

            // Зберігаємо в БД
            CryptoPrice record = new CryptoPrice();
            record.setCryptoFrom(from);
            record.setCurrencyTo(to);
            record.setPrice(price);
            repository.save(record);

            return "{\"price\": \"" + price + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Could not fetch price\"}";
        }
    }

    public void exportToExcel(HttpServletResponse response) {
        try {
            List<CryptoPrice> prices = repository.findAll();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Crypto Prices");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("From");
            header.createCell(1).setCellValue("To");
            header.createCell(2).setCellValue("Price");
            header.createCell(3).setCellValue("Timestamp");

            int rowNum = 1;
            for (CryptoPrice p : prices) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(p.getCryptoFrom());
                row.createCell(1).setCellValue(p.getCurrencyTo());
                row.createCell(2).setCellValue(p.getPrice().doubleValue());
                row.createCell(3).setCellValue(p.getTimestamp().toString());
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=crypto_prices.xlsx");
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            workbook.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
