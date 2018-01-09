package com.teamknp.hotel.entity;

import javax.persistence.*;
import java.time.LocalDate;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    float amount;
    @OneToMany
    Reservation reservation;
    LocalDate date;
    @OneToMany
    PaymentType paymentType;
}
