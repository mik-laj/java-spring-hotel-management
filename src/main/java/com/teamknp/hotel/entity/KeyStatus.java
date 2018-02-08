package com.teamknp.hotel.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class KeyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @DateTimeFormat(pattern="HH:mm, dd-MM-yyyy")
    LocalDateTime timeGiven;
    @DateTimeFormat(pattern="HH:mm, dd-MM-yyyy")
    LocalDateTime timeReturned;
    @ManyToOne
    Reservation reservation;
}
