package com.teamknp.hotel.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Data
@EntityFormat("#{name} (EAN: #{ean})")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String ean;

    String name;

    @NumberFormat(pattern = "#,###,###,###.##")
    BigDecimal price;

    int available;
}
