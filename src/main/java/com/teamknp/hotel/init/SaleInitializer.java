package com.teamknp.hotel.init;

import com.teamknp.hotel.entity.Product;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.SoldItem;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.repository.ProductRepository;
import com.teamknp.hotel.repository.ReservationRepository;
import com.teamknp.hotel.repository.SoldItemRepository;
import com.teamknp.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@Order(1)
public class SaleInitializer implements DataLoader {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SoldItemRepository soldItemRepository;

    @Override
    public void load() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<User> users = userRepository.findAll();
        User user = users.get(0);

        if(soldItemRepository.count() != 0){
            return;
        }

        for (int i = 0; i < reservations.size(); i++) {
            if (i % 2 == 0) continue;
            ;
            Reservation reservation = reservations.get(i);

            int soldItemCount = 5 + ((i * 7 % products.size()));
            for (int j = 0; j < soldItemCount; j++) {
                Product product = products.get(((i + j) * 11 % products.size()));

                SoldItem item = new SoldItem();
                item.setPrice(product.getPrice());
                item.setCount(1 + ((i + j) * 11) % 8);
                item.setCreatedBy(user);
                item.setCreatedDate(new Date());
                item.setProduct(product);
                item.setReservation(reservation);
                soldItemRepository.save(item);
            }
        }
    }
}
