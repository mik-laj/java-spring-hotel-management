package com.teamknp.hotel.entity;

import javax.persistence.*;

public class ReservationRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    Room room;
    @ManyToMany
    Reservation reseravation;
}
