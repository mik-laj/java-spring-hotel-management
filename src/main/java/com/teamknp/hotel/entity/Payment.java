package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@EntityFormat("#{type} #{amount} PLN")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Digits(integer=6, fraction=2)
    BigDecimal amount;
    @ManyToOne
    Reservation reservation;
    @DateTimeFormat(pattern="dd-MM-yyyy")
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
