package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EntityFormat("Reservation #{id}")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    Client client;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    LocalDate startDate;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
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
