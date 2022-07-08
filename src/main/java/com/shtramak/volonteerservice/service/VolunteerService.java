package com.shtramak.volonteerservice.service;

import java.util.List;
import java.util.UUID;

import com.shtramak.volonteerservice.entity.BankAccount;
import com.shtramak.volonteerservice.entity.Donate;
import com.shtramak.volonteerservice.entity.Volunteer;

public interface VolunteerService {

    UUID create(Volunteer volunteer);

    UUID createBankAccount(BankAccount bankAccount, String name);

    void donate(Donate donate, String name);

    List<Volunteer> allVolunteers();

    Volunteer volunteerByName(String name);
}
