package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;
    @OneToMany(mappedBy="client", cascade=CascadeType.ALL)
    List<Address> addresses;
    @OneToMany(mappedBy="client", cascade=CascadeType.ALL)
    List<Reservation> reservations;


}
