package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@EntityFormat("#{roomNumber}")
@ToString(exclude = "reservations")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique=true)
    String roomNumber;
    int bedsSingleCount;
    int bedsDoubleCount;
    @Digits(integer=5, fraction=2)
    BigDecimal cost;
    @OneToMany(mappedBy="room", cascade=CascadeType.PERSIST)
    List<Reservation> reservations;

}
