package com.teamknp.hotel.init;

import com.teamknp.hotel.entity.Delivery;
import com.teamknp.hotel.entity.DeliveryItem;
import com.teamknp.hotel.entity.Product;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.repository.DeliveryRepository;
import com.teamknp.hotel.repository.ProductRepository;
import com.teamknp.hotel.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Order(1)
public class WarehouseInitializer implements DataLoader{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DeliveryRepository deliveryRepository;


    @Override
    public void load() {
        if (productRepository.count() == 0) {
            for(int i = 0; i < 50; i++) {
                Product product = new Product();
                product.setName(String.format("Product #%03d", i));
                product.setEan(String.format("590%010d", i));
                product.setPrice(new BigDecimal(10 + (10 * i % 200)));
                product.setAvailable((i * 8) % 12);
                product.setWarning((i * 8) % 10);

                productRepository.save(product);
            }
        }

        if (deliveryRepository.count() == 0) {
            User user = userRepository.findAll().get(0);
            List<Product> products = productRepository.findAll();
            for(int i = 0; i < 3; i++){
                Delivery delivery = new Delivery();

                List<DeliveryItem> items = new ArrayList<DeliveryItem>();
                for(int j = 0; j < 6; j++){
                    DeliveryItem item = new DeliveryItem();
                    item.setCount(1 + (((i + j) * 22) % 6));
                    item.setDelivery(delivery);
                    item.setProduct(products.get(((i + j) * 6) % products.size()));
                    items.add(item);
                }
                delivery.setItemList(items);
                delivery.setCreatedBy(user);
                delivery.setLastModifiedBy(user);
                delivery.setCreatedDate(new Date());
                delivery.setLastModifiedDate(new Date());
                deliveryRepository.save(delivery);
            }
        }

    }
}
