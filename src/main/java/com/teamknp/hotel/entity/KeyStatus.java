package com.teamknp.hotel.entity;

import javax.persistence.*;
import java.time.LocalDate;

public class KeyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate timeGiven;
    LocalDate timeReturned;
    @OneToMany
    @JoinTable
    ReservationRoom reservationRoom;
}
