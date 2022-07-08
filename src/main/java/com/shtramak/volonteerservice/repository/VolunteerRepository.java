package com.shtramak.volonteerservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shtramak.volonteerservice.entity.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, UUID> {

    Optional<Volunteer> findByName(String name);
}
