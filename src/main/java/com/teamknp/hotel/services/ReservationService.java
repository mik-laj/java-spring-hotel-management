package com.teamknp.hotel.services;

import com.teamknp.hotel.controller.Command;
import com.teamknp.hotel.entity.Address;
import com.teamknp.hotel.entity.Client;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.repository.AddressRepository;
import com.teamknp.hotel.repository.ClientRepository;
import com.teamknp.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ReservationService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ReservationRepository reservationRepository;

   public Reservation saveNewReservation(Command command) {

       Client client = new Client();
       client.setFirstName(command.getFirstName());
       client.setLastName(command.getLastName());
        // save client
       clientRepository.save(client);

       Address address = new Address();
       address.setCity(command.getCity());
       address.setPostcode(command.getPostcode());
       address.setProvince(command.getProvince());
       address.setCountry(command.getCountry());
       address.setHouseNo(command.getHouseNo());
       address.setStreet(command.getStreet());
       addressRepository.save(address);


       Reservation reservation = new Reservation();
       reservation.setStatus(Reservation.Status.PENDING);
       reservation.setStartDate(command.getStart());
       reservation.setEndDate(command.getEnd());
       reservation.setClient(client);
       reservation.setAddress(address);
       // Save reservation
       reservationRepository.save(reservation);

       return reservation;

   }

}
