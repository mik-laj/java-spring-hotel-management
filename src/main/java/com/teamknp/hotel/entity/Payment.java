package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@EntityFormat("#{type} #{amount} PLN")
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
