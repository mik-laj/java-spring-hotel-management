package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.Product;
import lombok.Data;

@Data
public class ProductChoiceItem {
    Long id;
    String label;

    static ProductChoiceItem from(Product product) {
        ProductChoiceItem p = new ProductChoiceItem();
        p.id = product.getId();
        p.label = product.getName();
        return p;
    }
}
