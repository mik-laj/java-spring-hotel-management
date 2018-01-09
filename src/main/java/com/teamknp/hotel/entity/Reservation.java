package com.teamknp.hotel.entity;

import javax.persistence.*;
import javax.persistence.OneToMany;
import java.time.LocalDate;

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToMany
    Client client;
    LocalDate startDate;
    LocalDate endDate;
    String status;
    String notes;

}
