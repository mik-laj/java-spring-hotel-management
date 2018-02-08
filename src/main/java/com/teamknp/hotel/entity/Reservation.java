package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityFormat("Reservation #{id}")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Client client;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    LocalDate startDate;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    LocalDate endDate;
    @DateTimeFormat(pattern="HH:mm, dd-MM-yyyy")
    LocalDateTime checkInTime;
    @DateTimeFormat(pattern="HH:mm, dd-MM-yyyy")
    LocalDateTime checkOutTime;
    @Enumerated(EnumType.STRING)
    Status status;
    @ManyToOne
    Address address;
    @ManyToOne
    Room room;
    @OneToMany(mappedBy = "reservation")
    List<KeyStatus> keyStatuses;

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
        CHECK_OUT_OVERDUE,
        CHECK_OUT_OVERDUE_RESOLVED,
        CANCELLED,
        FINISHED
    }

}
