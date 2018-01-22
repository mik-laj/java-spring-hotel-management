package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Address;
import com.teamknp.hotel.entity.Client;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.ReservationEditForm;
import com.teamknp.hotel.form.SelectRoomClientForm;
import com.teamknp.hotel.repository.AddressRepository;
import com.teamknp.hotel.repository.ClientRepository;
import com.teamknp.hotel.repository.ReservationRepository;
import com.teamknp.hotel.repository.RoomRepository;
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

    @Autowired
    RoomRepository roomRepository;

    public Page<Reservation> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public Page<Reservation> search(String query, Pageable pageable) {
        query = "%" + query + "%";
        return reservationRepository.search(query, pageable);
    }
    public void update(Reservation entity, ReservationEditForm formData) {

        Reservation reservation = entity;
        //tu byc moze miejsce na status
        reservation.setNotes(formData.getNotes());
        reservationRepository.save(entity);

        Client client = entity.getClient();
        client.setFirstName(formData.getFirstName());
        client.setLastName(formData.getLastName());
        clientRepository.save(client);

        Address address = entity.getAddress();
        address.setStreetName(formData.getStreet());
        address.setCity(formData.getCity());
        address.setCountry(formData.getCountry());
        address.setHouseNo(formData.getHouseNo());
        address.setPostcode(formData.getPostcode());
        address.setProvince(formData.getProvince());
        addressRepository.save(address);


    }

    public Reservation saveNewReservation(SelectRoomClientForm reservationForm) {

        Client client = new Client();
        client.setFirstName(reservationForm.getFirstName());
        client.setLastName(reservationForm.getLastName());
        // addClaim client
        clientRepository.save(client);

        Address address = new Address();
        address.setCity(reservationForm.getCity());
        address.setPostcode(reservationForm.getPostcode());
        address.setProvince(reservationForm.getProvince());
        address.setCountry(reservationForm.getCountry());
        address.setHouseNo(reservationForm.getHouseNo());
        address.setStreetName(reservationForm.getStreet());
        addressRepository.save(address);

        Reservation reservation = new Reservation();
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setStartDate(reservationForm.getStart());
        reservation.setEndDate(reservationForm.getEnd());
        reservation.setClient(client);
        reservation.setAddress(address);
        reservation.setNotes(reservationForm.getNotes());
        reservation.setRoom(reservation.getRoom());
        reservationRepository.save(reservation);

        // Save reservation

        return reservation;

    }

    @Transactional
    public void expireReservationStatuses(LocalDate expirationDate) {
        reservationRepository.expireReservationStatuses(expirationDate);
    }

}
