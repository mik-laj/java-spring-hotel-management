package com.teamknp.hotel.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
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
    @OneToMany(mappedBy = "reservation")
    List<ReservationRoom> reservationRoom;
    String notes;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        CANCELLED,
        FINISHED
    }

}
