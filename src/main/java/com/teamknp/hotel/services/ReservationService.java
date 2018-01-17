package com.teamknp.hotel.services;

import com.teamknp.hotel.controller.Command;
import com.teamknp.hotel.entity.Address;
import com.teamknp.hotel.entity.Client;
import com.teamknp.hotel.entity.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
   // Reservation getReservation(int id);
   // void saveReservation(Reservation r);
   public Reservation saveNewReservation(Command command) {
       Client client = new Client();
       client.setFirstName("");
       client.setLastName("AAA");
        // save client

       Address address = new Address();
       address.setClient(client);
       /// Save

       Reservation reservation = new Reservation();
       reservation.setStartDate(command.getStart());
       reservation.setEndDate(command.getEnd());
       reservation.setClient(client);
       // Save reservation

       return reservation;

   }

}
