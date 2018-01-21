package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Product;
import com.teamknp.hotel.form.ProductForm;
import com.teamknp.hotel.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> search(String keywords, Pageable pageable) {
        keywords = "%" + keywords + "%";
        return productRepository.search(keywords, pageable);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Product save(ProductForm formData) {
        Product p = new Product();
        p.setName(formData.getName());
        p.setEan(formData.getEan());
        p.setPrice(formData.getPrice());
        productRepository.save(p);
        return p;
    }
}
