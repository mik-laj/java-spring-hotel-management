package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Address;
import com.teamknp.hotel.entity.Client;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.ReservationForm;
import com.teamknp.hotel.repository.AddressRepository;
import com.teamknp.hotel.repository.ClientRepository;
import com.teamknp.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ReservationRepository reservationRepository;

   public Reservation saveNewReservation(ReservationForm reservationForm) {

       Client client = new Client();
       client.setFirstName(reservationForm.getFirstName());
       client.setLastName(reservationForm.getLastName());
        // save client
       clientRepository.save(client);

       Address address = new Address();
       address.setCity(reservationForm.getCity());
       address.setPostcode(reservationForm.getPostcode());
       address.setProvince(reservationForm.getProvince());
       address.setCountry(reservationForm.getCountry());
       address.setHouseNo(reservationForm.getHouseNo());
       address.setStreet(reservationForm.getStreet());
       addressRepository.save(address);


       Reservation reservation = new Reservation();
       reservation.setStatus(Reservation.Status.PENDING);
       reservation.setStartDate(reservationForm.getStart());
       reservation.setEndDate(reservationForm.getEnd());
       reservation.setClient(client);
       reservation.setAddress(address);
       // Save reservation
       reservationRepository.save(reservation);

       return reservation;

   }

}
