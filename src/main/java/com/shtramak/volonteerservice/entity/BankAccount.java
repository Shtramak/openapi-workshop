package com.shtramak.volonteerservice.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
public class BankAccount {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    private String bankName;
    private String number;

    @ElementCollection
    @CollectionTable(name = "currency_amount", joinColumns = @JoinColumn(name = "account_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Setter(AccessLevel.PRIVATE)
    private Map<CurrencyType, BigDecimal> currencyAmount = new HashMap<>();

    public void deposit(CurrencyType currencyType, BigDecimal amount) {
        Optional.ofNullable(currencyAmount.get(currencyType))
            .ifPresentOrElse(currentAmount -> currencyAmount.put(currencyType, currentAmount.add(amount)),
                () -> currencyAmount.put(currencyType, amount));
    }

}