package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String houseNo;
    String postcode;
    String city;
    String street;
    String province;
    String country;
    @ManyToOne
    Client client;
}
