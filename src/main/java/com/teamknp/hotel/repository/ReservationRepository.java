package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("select r from Reservation r inner join r.client as c where c.firstName = :query or c.lastName = :query")
    Page<Reservation> search(@Param("query") String query, Pageable pageable);
}
