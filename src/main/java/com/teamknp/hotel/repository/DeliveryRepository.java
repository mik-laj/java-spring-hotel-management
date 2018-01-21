package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("SELECT d from Delivery d JOIN d.createdBy as u WHERE CAST(d.id AS string) LIKE :query OR u.username LIKE :query")
    Page<Delivery> search(@Param("query") String query, Pageable pageable);
}
