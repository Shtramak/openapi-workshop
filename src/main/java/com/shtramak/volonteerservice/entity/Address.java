package com.shtramak.volonteerservice.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Address {

    @Id
    private UUID id;

    @OneToOne(optional = false)
    @MapsId
    private Volunteer volunteer;

    private String city;
    private String zipCode;
}