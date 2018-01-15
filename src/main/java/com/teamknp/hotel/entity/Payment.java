package com.teamknp.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    float amount;
    @ManyToOne
    Reservation reservation;
    LocalDate date;
    @Enumerated(EnumType.STRING)
    Type type;

    public enum Type {
        CASH,
        CREDIT_CARD,
        TRANSFER,
        CHEQUE
    }
}
