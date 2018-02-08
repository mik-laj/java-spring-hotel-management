package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r inner join r.client as c where c.firstName LIKE :query or c.lastName LIKE :query")
    Page<Reservation> search(@Param("query") String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Reservation r set r.status = 'EXPIRED' where r.status = 'PENDING' and r.startDate <= :expirationDate")
    void expireReservationStatuses(@Param("expirationDate") LocalDate expirationDate);

    @Transactional
    @Modifying
    @Query(value = "update Reservation r set r.status = 'CHECK_OUT_OVERDUE' where r.status = 'IN_PROGRESS' and r.endDate <= :overdueDate")
    void updateOverdueCheckOuts(@Param("overdueDate") LocalDate overdueDate);

    List<Reservation> findAllByStartDateEquals(LocalDate localDate);

    List<Reservation> findAllByEndDateEquals(LocalDate localDate);
}
