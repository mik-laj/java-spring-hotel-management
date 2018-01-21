package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EntityFormat("#{roomNumber}")
@ToString(exclude = "reservations")
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
