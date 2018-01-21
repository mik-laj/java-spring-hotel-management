package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("select r from Reservation r inner join r.client as c where c.firstName LIKE :query or c.lastName LIKE :query")
    Page<Reservation> search(@Param("query") String query, Pageable pageable);

    @Modifying
    @Query(value = "update Reservation r set r.status = 'TIMED_OUT' where r.status = 'PENDING' and r.startDate < :timeoutDate")
    void timeoutReservationStatuses(@Param("timeoutDate")LocalDate timeoutDate);
}
