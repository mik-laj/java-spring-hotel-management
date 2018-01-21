package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("FROM Product p WHERE p.name LIKE :query OR p.ean LIKE :query")
    Page<Product> search(@Param("query") String keywords, Pageable pageable);

}
