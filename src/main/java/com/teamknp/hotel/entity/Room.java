package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique=true)
    String roomNumber;
    int bedsSingleCount;
    int bedsDoubleCount;
    int cost;
    @OneToMany(mappedBy="room", cascade=CascadeType.PERSIST)
    List<Reservation> reservations;

}
