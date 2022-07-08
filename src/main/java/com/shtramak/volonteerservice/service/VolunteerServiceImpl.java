package com.shtramak.volonteerservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shtramak.volonteerservice.entity.BankAccount;
import com.shtramak.volonteerservice.entity.CurrencyType;
import com.shtramak.volonteerservice.entity.Donate;
import com.shtramak.volonteerservice.entity.Volunteer;
import com.shtramak.volonteerservice.exception.InvalidDonationAmountException;
import com.shtramak.volonteerservice.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository repository;

    @Override
    public List<Volunteer> allVolunteers() {
        return repository.findAll();
    }

    @Override
    public Volunteer volunteerByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new NoSuchElementException(String.format("No Volunteer found with name [%s]", name)));
    }

    @Override
    public UUID create(Volunteer volunteer) {
        return repository.save(volunteer).getId();
    }

    @Override
    public UUID createBankAccount(BankAccount bankAccount, String name) {
        Volunteer volunteer = volunteerByName(name);
        volunteer.addBankAccount(bankAccount);
        repository.flush();
        return volunteer.getBankAccounts().stream()
            .filter(account -> Objects.equals(bankAccount.getBankName(), account.getBankName()))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(
                String.format("No account in bank=[%s] found", bankAccount.getBankName())))
            .getId();
    }

    @Override
    public void donate(Donate donate, String name) {
        verifyDonationAmount(donate.getAmount());
        Volunteer volunteer = volunteerByName(name);
        BankAccount bankAccount = volunteer.getBankAccounts().stream()
            .filter(account -> Objects.equals(donate.getBankName(), account.getBankName()))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(
                String.format("%s doesn't have an account in bank %s", volunteer.getName(),
                    donate.getBankName())));
        CurrencyType donateCurrency = CurrencyType.currencyTypeFrom(donate.getCurrency());
        BigDecimal roundedAmount = donate.getAmount().setScale(2, RoundingMode.HALF_DOWN);
        bankAccount.deposit(donateCurrency, roundedAmount);
    }

    private void verifyDonationAmount(BigDecimal amount) {
        if (Objects.isNull(amount) || amount.setScale(2, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDonationAmountException(String.format("Provided amount=[%s] is not acceptable", amount));
        }
    }

}
