package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class KeyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime timeGiven;
    LocalDateTime timeReturned;
    @ManyToOne
    Room room;
}
