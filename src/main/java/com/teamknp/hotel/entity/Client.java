package com.teamknp.hotel.entity;

import javax.persistence.*;
import java.util.List;

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable()
    List<Address> addressList;

}
