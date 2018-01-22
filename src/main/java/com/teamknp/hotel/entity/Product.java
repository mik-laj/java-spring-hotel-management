package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Data
@EntityFormat("#{name}")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column()
    String ean;

    @Column(nullable = false)
    String name;

    @NumberFormat(pattern = "#,###,###,###.##")
    BigDecimal price;

    @Column(nullable = false)
    Integer available;

    @Column(nullable = false)
    Integer warning;
}
