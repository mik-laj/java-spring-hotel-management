package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.KeyStatus;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.repository.KeyStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KeyStatusService {
    @Autowired
    KeyStatusRepository keyStatusRepository;

    public void giveOutKey(Reservation reservation, LocalDateTime when) {
        KeyStatus keyStatus = new KeyStatus();
        keyStatus.setRoom(reservation.getRoom());
        keyStatus.setTimeGiven(when);
        keyStatusRepository.save(keyStatus);
    }

    public void returnKey(KeyStatus keyStatus, LocalDateTime when) {
        keyStatus.setTimeReturned(when);
        keyStatusRepository.save(keyStatus);
    }

    public List<KeyStatus> findAll() {
        return keyStatusRepository.findAll();
    }



    public void delete(KeyStatus keyStatus) {
        keyStatusRepository.delete(keyStatus);
    }
}
