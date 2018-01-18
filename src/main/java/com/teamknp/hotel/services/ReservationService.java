package com.teamknp.hotel.services;

import com.teamknp.hotel.controller.Command;
import com.teamknp.hotel.entity.Address;
import com.teamknp.hotel.entity.Client;
import com.teamknp.hotel.entity.Reservation;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ReservationService {

    @PersistenceContext
    private EntityManager m;

   public Reservation saveNewReservation(Command command) {

       Client client = new Client();
       client.setFirstName(command.getFirstName());
       client.setLastName(command.getLastName());
        // save client

       Address address = new Address();
       address.setCity(command.getCity());
       address.setPostcode(command.getPostcode());
       address.setProvince(command.getProvince());
       address.setCountry(command.getCountry());
       address.setHouseNo(command.getHouseNo());
       address.setStreet(command.getStreet());
       address.setClient(client);

       Reservation reservation = new Reservation();
       reservation.setStatus(Reservation.Status.PENDING);
       reservation.setStartDate(command.getStart());
       reservation.setEndDate(command.getEnd());
       reservation.setClient(client);
       // Save reservation

       return reservation;

   }

}
