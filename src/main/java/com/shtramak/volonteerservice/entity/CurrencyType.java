package com.shtramak.volonteerservice.entity;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public enum CurrencyType {
    UAH, EUR, USD;

    public static CurrencyType currencyTypeFrom(String currencyString) {
        return Stream.of(CurrencyType.values())
            .filter(currencyType -> currencyType.name().equalsIgnoreCase(currencyString))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(
                String.format("Currency %s not supported. Please, choose from [UAH, USD, EUR]", currencyString)));
    }
}
