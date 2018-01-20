package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    Client client;
    LocalDate startDate;
    LocalDate endDate;
    @Enumerated(EnumType.STRING)
    Status status;
    @ManyToOne
    Address address;
    @ManyToOne
    Room room;
    String notes;

    public enum Status {
        PENDING,
        TIMED_OUT,
        IN_PROGRESS,
        CANCELLED,
        FINISHED
    }

}
