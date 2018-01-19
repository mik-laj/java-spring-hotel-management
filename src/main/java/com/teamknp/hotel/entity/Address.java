package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String houseNo;
    String postcode;
    String streetName;
    String city;
    String province;
    String country;
    @OneToMany(mappedBy = "address")
    List<Reservation> reservation;
}
