package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
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
