package com.teamknp.hotel.controller;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Command {
    // reservation
    LocalDate start;
    LocalDate end;
    String room;
    // address
    String houseNo;
    String postcode;
    String city;
    String province;
    String country;

    // Client
    String firstName;
    String lastName;
}