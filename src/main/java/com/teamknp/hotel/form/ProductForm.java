package com.teamknp.hotel.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductForm {
    String ean;

    String name;

    BigDecimal price;
}
