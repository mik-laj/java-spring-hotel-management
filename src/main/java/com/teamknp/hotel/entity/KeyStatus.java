package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
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
