package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.DeliveryItem;
import lombok.Data;

@Data
public class DeliveryItemForm {
    ProductChoiceItem product;
    Integer count;

    public static DeliveryItemForm from(DeliveryItem deliveryItem) {
        DeliveryItemForm d = new DeliveryItemForm();
        d.count = deliveryItem.getCount();
        d.product = ProductChoiceItem.from(deliveryItem.getProduct());
        return d;
    }
}
