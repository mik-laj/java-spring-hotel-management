package com.teamknp.hotel.form;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationForm {
    // reservation
    String notes;
    LocalDate start;
    LocalDate end;
    String room;
    // address
    String houseNo;
    String postcode;
    String city;
    String street;
    String province;
    String country;

    // Client
    String firstName;
    String lastName;
}
