package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Product;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.SoldItem;
import com.teamknp.hotel.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldItemRepository extends JpaRepository<SoldItem, Integer> {

    List<SoldItem> findAllByReservation(Reservation reservation);
}

