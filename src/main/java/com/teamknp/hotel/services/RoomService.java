package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    public Page<Room> search(String keyword, Pageable pageable) {
        return roomRepository.findByRoomNumberLike("%" + keyword + "%", pageable);
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }

    public List<Room> findAvailableRoomByDate(LocalDate start, LocalDate end) {
        return roomRepository.findAvailableRoomByDate(Date.valueOf(start), Date.valueOf(start));
    }
}
