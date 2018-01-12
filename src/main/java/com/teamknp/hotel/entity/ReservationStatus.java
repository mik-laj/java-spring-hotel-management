package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ReservationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotNull
    @Column(unique=true)
    String name;
}
