package com.teamknp.hotel.entity;

import javax.persistence.*;

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String roomNumber;
    int bedsSingleCount;
    int bedsDoubleCount;
    float cost;


}
