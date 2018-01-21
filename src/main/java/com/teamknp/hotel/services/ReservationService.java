package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Address;
import com.teamknp.hotel.entity.Client;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.ReservationForm;
import com.teamknp.hotel.repository.AddressRepository;
import com.teamknp.hotel.repository.ClientRepository;
import com.teamknp.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ReservationService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

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

    @Transactional
    public void updateReservationStatuses(LocalDate timeoutDate) {
        reservationRepository.timeoutReservationStatuses(timeoutDate);
    }

}
