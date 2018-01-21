package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
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
    String street;
    String province;
    String country;
    @OneToMany(mappedBy = "address")
    List<Reservation> reservation;
}
