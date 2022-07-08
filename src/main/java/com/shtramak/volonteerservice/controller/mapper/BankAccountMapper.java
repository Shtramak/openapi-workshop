package com.shtramak.volonteerservice.controller.mapper;

import com.bobocode.openapi.dto.BankAccountDto;
import com.shtramak.volonteerservice.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BankAccountMapper {

    @Mapping(source = "accountNumber",target = "number")
    BankAccount toBankAccount(BankAccountDto bankAccountDto);

    @Mapping(target = "accountNumber",source = "number")
    BankAccountDto toBankAccountDto(BankAccount bankAccount);
}
