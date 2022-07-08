package com.shtramak.volonteerservice.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bobocode.openapi.controller.VolunteersApi;
import com.bobocode.openapi.dto.BankAccountDto;
import com.bobocode.openapi.dto.DonateDto;
import com.bobocode.openapi.dto.VolunteerDto;
import com.bobocode.openapi.dto.VolunteerFullInfoDto;
import com.shtramak.volonteerservice.controller.mapper.BankAccountMapper;
import com.shtramak.volonteerservice.controller.mapper.VolunteerMapper;
import com.shtramak.volonteerservice.entity.Donate;
import com.shtramak.volonteerservice.entity.Volunteer;
import com.shtramak.volonteerservice.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RestController
@RequiredArgsConstructor
public class VolunteerController implements VolunteersApi {

    private final VolunteerService volunteerService;

    private final VolunteerMapper volunteerMapper;

    private final BankAccountMapper bankAccountMapper;

    @Override
    public ResponseEntity<List<VolunteerFullInfoDto>> allVolunteers() {
        List<Volunteer> volunteers = volunteerService.allVolunteers();
        return ResponseEntity.ok(volunteers.stream()
            .map(volunteerMapper::toVolunteerFullInfoDto)
            .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Void> createBankAccount(String name, @Valid BankAccountDto bankAccountDto) {
        volunteerService.createBankAccount(bankAccountMapper.toBankAccount(bankAccountDto), name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UUID> createVolunteer(@Valid VolunteerDto volunteerDto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(volunteerService.create(new ModelMapper().map(volunteerDto, Volunteer.class)));
    }

    @Override
    public ResponseEntity<Void> donate(String name, @Valid DonateDto donateDto) {
        volunteerService.donate(new ModelMapper().map(donateDto, Donate.class),name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VolunteerFullInfoDto> volunteerByName(String name) {
        Volunteer volunteer = volunteerService.volunteerByName(name);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(volunteerMapper.toVolunteerFullInfoDto(volunteer));
    }
}
