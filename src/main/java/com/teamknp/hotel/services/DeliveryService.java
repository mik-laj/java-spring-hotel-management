package com.teamknp.hotel.services;


import com.teamknp.hotel.entity.Delivery;
import com.teamknp.hotel.entity.DeliveryItem;
import com.teamknp.hotel.form.DeliveryForm;
import com.teamknp.hotel.repository.DeliveryRepository;
import com.teamknp.hotel.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.stream.Collectors.toList;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    public Page<Delivery> search(String query, Pageable pageable) {
        query = "%" + query + "%";
        return deliveryRepository.search(query, pageable);
    }

    public Page<Delivery> findAll(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    public void delete(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    public Delivery save(DeliveryForm formData) {
        Delivery delivery = new Delivery();
        bindFormData(formData, delivery);
        delivery.setCreatedBy(userService.getCurrentUser());
        delivery.setCreatedDate(new Date());
        deliveryRepository.save(delivery);

        return delivery;
    }

    private void bindFormData(DeliveryForm formData, Delivery delivery) {
        delivery.addAll(formData.getItems().stream().filter(d -> d.getProduct() != null).map(d -> {
            DeliveryItem item = new DeliveryItem();
            item.setCount(d.getCount());
            item.setProduct(productRepository.getOne(d.getProduct().getId()));
            item.setDelivery(delivery);
            return item;
        }).collect(toList()));
        delivery.setLastModifiedBy(userService.getCurrentUser());
        delivery.setLastModifiedDate(new Date());
    }

    public void update(Delivery delivery, DeliveryForm formData) {
        delivery.clear();
        bindFormData(formData, delivery);
        deliveryRepository.save(delivery);
    }
}
