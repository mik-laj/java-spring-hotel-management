package com.teamknp.hotel.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(exclude = "reservation")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String houseNo;
    String postcode;
    String streetName;
    String city;
    String province;
    String country;
    @OneToMany(mappedBy = "address")
    List<Reservation> reservation;
}
