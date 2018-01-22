package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.SoldItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldItemRepository extends JpaRepository<SoldItem, Integer> {

    List<SoldItem> findAllByReservation(Reservation reservation);
}

