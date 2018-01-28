package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findByRoomNumberLike(String name, Pageable pageable);

    @Query(value = "SELECT\n" +
            "  room.*\n" +
            "FROM\n" +
            "  room\n" +
            "  LEFT JOIN\n" +
            "  reservation\n" +
            "    ON (\n" +
            "    reservation.room_id = room.id AND\n" +
            "    NOT (\n" +
            "      (reservation.start_date < ?1 and reservation.end_date < ?1)\n" +
            "      OR\n" +
            "      (reservation.start_date > ?2 and reservation.end_date > ?2)\n" +
            "    )\n" +
            "       OR\n"+
            "       (reservation.status='EXPIRED' and reservation.status='CANCELLED' and reservation.status='FINISHED')"+
            "    )\n" +
            "WHERE\n" +
            "  reservation.room_id IS NULL" , nativeQuery = true)
    List<Room> findAvailableRoomByDate(Date start, Date end);
}
