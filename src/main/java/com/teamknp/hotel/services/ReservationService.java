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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

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

        reservation.setRoom(roomRepository.findOne(reservationForm.getRoomId()));
        reservationRepository.save(reservation);

        // Save reservation

        return reservation;
    }

    @Transactional
    public void expireReservationStatuses(LocalDate expirationDate) {
        reservationRepository.expireReservationStatuses(expirationDate);
    }

    @Transactional
    public void updateOverdueCheckOuts(LocalDate overdueDate) {
        reservationRepository.updateOverdueCheckOuts(overdueDate);
    }

    public BigDecimal getReservationCost(Reservation reservation) {
        int lengthOfReservation = Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
        return reservation.getRoom().getCost().multiply(new BigDecimal(lengthOfReservation));
    }

    public void cancelReservation(Reservation reservation) throws IllegalArgumentException {
        if (canBeCancelled(reservation)) {
            reservation.setStatus(Reservation.Status.CANCELLED);
            reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("Reservation cannot be cancelled: status is not " + Reservation.Status.PENDING + "!");
        }
    }

    public void checkInReservation(Reservation reservation) throws IllegalArgumentException {
        if (canBeCheckedIn(reservation)) {
            reservation.setStatus(Reservation.Status.IN_PROGRESS);
            reservation.setCheckInTime(LocalDateTime.now());
            reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("Reservation check-in invalid: status is either not " + Reservation.Status.PENDING + " or the reservation start date " + reservation.getStartDate() + " is not today (" + LocalDate.now() + ")!");
        }
    }

    public void checkOutReservation(Reservation reservation) throws IllegalArgumentException {
        if (canBeCheckedOut(reservation)) {
            reservation.setStatus(Reservation.Status.FINISHED);
            reservation.setCheckOutTime(LocalDateTime.now());
            reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("Reservation check-out invalid: status is either not " + Reservation.Status.IN_PROGRESS + " or the reservation end date " + reservation.getEndDate() + " is not today (" + LocalDate.now() + ")!");
        }
    }

    public void resolveReservation(Reservation reservation) {
        if (canBeResolved(reservation)) {
            reservation.setStatus(Reservation.Status.CHECK_OUT_OVERDUE_RESOLVED);
            reservation.setCheckOutTime(LocalDateTime.now());
            reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("Reservation resolve invalid: status is not " + Reservation.Status.CHECK_OUT_OVERDUE + "!");
        }
    }

    public boolean canBeCheckedIn(Reservation reservation) {
        return (reservation.getStatus() == Reservation.Status.PENDING && reservation.getStartDate().isEqual(LocalDate.now()));
    }

    public boolean canBeCancelled(Reservation reservation) {
        return (reservation.getStatus() == Reservation.Status.PENDING);
    }

    public boolean canBeCheckedOut(Reservation reservation) {
        Reservation.Status currentStatus = reservation.getStatus();
        return (currentStatus == Reservation.Status.IN_PROGRESS && reservation.getEndDate().isEqual(LocalDate.now()));
    }

    public boolean canBeResolved(Reservation reservation) {
        return reservation.getStatus() == Reservation.Status.CHECK_OUT_OVERDUE;
    }

    public boolean canKeyBeAdded(Reservation reservation) {
        Reservation.Status currentStatus = reservation.getStatus();
        return (currentStatus == Reservation.Status.IN_PROGRESS);
    }
}
