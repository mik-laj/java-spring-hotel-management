package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Integer> {
}
