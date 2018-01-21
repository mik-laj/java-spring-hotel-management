package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return roomRepository.findByRoomNumberLike("%"+ keyword + "%", pageable);
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }

    public List<Room> findAvailableRoomByDate(Date start, Date end) {
       return roomRepository.findAvailableRoomByDate(start, end);
    }
}
