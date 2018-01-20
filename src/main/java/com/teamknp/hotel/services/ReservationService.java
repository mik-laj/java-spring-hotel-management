package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Page<Reservation> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public Page<Reservation> search(String query, Pageable pageable) {
        return reservationRepository.search(query, pageable);
    }
}
