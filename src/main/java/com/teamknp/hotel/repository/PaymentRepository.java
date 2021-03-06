package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Payment;
import com.teamknp.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "select sum(amount) from payment p where p.reservation_id = :reservationId", nativeQuery = true)
    public BigDecimal getSumByReservationId(@Param("reservationId")Long reservationId);
    public List<Payment> findAllByReservation(Reservation reservation);
}
