package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Product;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.SoldItem;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.form.SaleProductForm;
import com.teamknp.hotel.repository.ProductRepository;
import com.teamknp.hotel.repository.SoldItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public class SaleService {
    @Autowired
    SoldItemRepository soldItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    public SoldItem sell(Reservation reservation, SaleProductForm formData) {
        Product product = productRepository.findOne(formData.getProduct());
        User currentUser = userService.getCurrentUser();

        SoldItem item = new SoldItem();
        item.setProduct(product);
        item.setCount(formData.getCount());
        item.setPrice(product.getPrice());
        item.setReservation(reservation);
        item.setCreatedBy(currentUser);
        item.setCreatedDate(new Date());
        soldItemRepository.save(item);

        return item;
    }

    public List<SoldItem> findAllByReservation(Reservation reservation) {
        return soldItemRepository.findAllByReservation(reservation);
    }
}
