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
    String notes;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        CANCELLED,
        FINISHED
    }

}
