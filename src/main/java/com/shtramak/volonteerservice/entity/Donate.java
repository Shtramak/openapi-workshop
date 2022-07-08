package com.shtramak.volonteerservice.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Donate {
    private String bankName;
    private String currency;
    private BigDecimal amount;
}
