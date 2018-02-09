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

    /**
     * Check-in time is 3pm (15:00).
     */
    @Scheduled(cron = "15 00 0 * * ?")
    public void expireReservationStatuses() {
        reservationService.expireReservationStatuses(LocalDate.now());
    }

    /**
     * Check-out time is noon.
     */
    @Scheduled(cron = "12 00 0 * * ?")
    public void updateOverdueCheckOuts() {reservationService.updateOverdueCheckOuts(LocalDate.now());}
}
