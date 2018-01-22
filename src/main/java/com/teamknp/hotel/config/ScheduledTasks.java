package com.teamknp.hotel.config;

import com.teamknp.hotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduledTasks {

    @Autowired
    ReservationService reservationService;

    @Scheduled(cron = "0 10 0 * * ?")
    public void expireReservationStatuses() {
        reservationService.expireReservationStatuses(LocalDate.now());
    }
}
