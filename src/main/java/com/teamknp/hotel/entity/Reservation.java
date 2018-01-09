package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.OneToMany;
import java.time.LocalDate;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToMany
    Client client;
    LocalDate startDate;
    LocalDate endDate;
    String status;
    String notes;

}
