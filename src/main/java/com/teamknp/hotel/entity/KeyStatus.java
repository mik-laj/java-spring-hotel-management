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
    int id;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime timeGiven;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime timeReturned;
    @ManyToOne
    Room room;
}
