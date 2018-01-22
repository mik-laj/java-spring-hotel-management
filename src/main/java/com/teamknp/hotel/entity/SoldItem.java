package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@EntityFormat("#{product} - #{count) - #{price}")
public class SoldItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    Reservation reservation;

    @JoinColumn(nullable = false)
    BigDecimal price = new BigDecimal(0.00);

    @JoinColumn(nullable = false)
    Integer count;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected User createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JoinColumn(nullable = false)
    protected Date createdDate;

    public BigDecimal getTotalValue() {
        return price.multiply(new BigDecimal(count));
    }
}