package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    Date startDate;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    Date endDate;
    @Enumerated(EnumType.STRING)
    Status status;
    @ManyToOne
    Address address;
    @ManyToOne
    Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    String notes;

    public enum Status {
        PENDING,
        EXPIRED,
        IN_PROGRESS,
        CANCELLED,
        FINISHED
    }

}
