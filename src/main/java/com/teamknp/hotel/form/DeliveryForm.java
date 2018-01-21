package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.Delivery;
import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class DeliveryForm {
    List<DeliveryItemForm> items;

    public static DeliveryForm from(Delivery delivery) {
        DeliveryForm f = new DeliveryForm();
        f.items = delivery.getItemList().stream().map(DeliveryItemForm::from).collect(toList());
        return f;
    }
}
