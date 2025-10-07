package com.example.cryptoApp.controller;

import com.example.cryptoApp.entity.Coin;
import com.example.cryptoApp.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @Bean
    public Coin init() {
        Coin c1 = new Coin();
        c1.setName("BITCOIN");
        c1.setQuantity(new BigDecimal("0.0045"));
        c1.setPrice(new BigDecimal(100));
        c1.setDateTime(new Timestamp(System.currentTimeMillis()));

        Coin c2 = new Coin();
        c2.setName("BITCOIN");
        c2.setQuantity(new BigDecimal("0.00085"));
        c2.setPrice(new BigDecimal(200));
        c2.setDateTime(new Timestamp(System.currentTimeMillis()));

        Coin c3 = new Coin();
        c3.setName("ETHEREUM");
        c3.setQuantity(new BigDecimal("0.0025"));
        c3.setPrice(new BigDecimal(500));
        c3.setDateTime(new Timestamp(System.currentTimeMillis()));

        coinRepository.insert(c1);
        coinRepository.insert(c2);
        coinRepository.insert(c3);
        return c1;
    }

    @GetMapping()
    public  ResponseEntity get(){
        return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable String name) {
        try {
            return new ResponseEntity<>(coinRepository.getByName(name), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Coin coin) throws Throwable {
        try {
            coin.setDateTime(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody Coin coin) throws Throwable {
        try {
            coin.setDateTime(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(coinRepository.update(coin), HttpStatus.OK);
        } catch (Exception e) {
            throw new Throwable(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            return new ResponseEntity<>(coinRepository.remove(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
