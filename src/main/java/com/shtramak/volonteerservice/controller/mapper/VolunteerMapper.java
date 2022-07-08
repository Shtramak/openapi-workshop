package com.shtramak.volonteerservice.controller.mapper;

import com.bobocode.openapi.dto.VolunteerFullInfoDto;
import com.shtramak.volonteerservice.entity.Volunteer;
import org.mapstruct.Mapper;

@Mapper(uses = BankAccountMapper.class)
public interface VolunteerMapper {

    VolunteerFullInfoDto toVolunteerFullInfoDto(Volunteer volunteer);

    Volunteer toVolunteer(VolunteerFullInfoDto volunteerFullInfoDto);
}
