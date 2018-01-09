package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReservationRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    Room room;
    @ManyToOne
    Reservation reservation;
}
