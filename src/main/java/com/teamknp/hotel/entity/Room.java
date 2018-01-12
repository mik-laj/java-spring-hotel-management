package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;

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
    float cost;
}
