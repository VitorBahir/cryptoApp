package com.example.cryptoApp.dto;

import java.math.BigDecimal;

public class CoinTransactionDTO {

    private String name;
    private BigDecimal quantity;

    public CoinTransactionDTO(String name, BigDecimal quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
