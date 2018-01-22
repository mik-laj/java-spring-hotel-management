package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@EntityFormat("#{type} #{amount} PLN")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    float amount;
    @ManyToOne
    Reservation reservation;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
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
