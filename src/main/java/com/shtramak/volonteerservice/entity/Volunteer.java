package com.shtramak.volonteerservice.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Getter
@Setter
public class Volunteer {

    @Id
    @GeneratedValue
    private UUID id;
    @NaturalId
    @Column(nullable = false, unique = true)
    private String name;
    private LocalDate dateOfCreation = LocalDate.now();
    @OneToOne(mappedBy = "volunteer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Address address;
    @OneToMany(mappedBy = "volunteer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Setter(AccessLevel.PRIVATE)
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public void addBankAccount(BankAccount bankAccount) {
        bankAccount.setVolunteer(this);
        bankAccounts.add(bankAccount);
    }

    public void setAddress(Address address) {
        address.setVolunteer(this);
        this.address = address;
    }
}
