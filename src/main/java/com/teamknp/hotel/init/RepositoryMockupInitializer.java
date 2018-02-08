package com.teamknp.hotel.init;

import com.teamknp.hotel.entity.*;
import com.teamknp.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryMockupInitializer implements DataLoader {
    private AddressRepository addressRepository;
    private ClientRepository clientRepository;
    private KeyStatusRepository keyStatusRepository;
    private PaymentRepository paymentRepository;
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;

    @Autowired
    public RepositoryMockupInitializer(AddressRepository addressRepository, ClientRepository clientRepository, KeyStatusRepository keyStatusRepository, PaymentRepository paymentRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.keyStatusRepository = keyStatusRepository;
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void load() {
        if (roomRepository.findAll().isEmpty()) {
            initRoomRepository();
        }
        if (clientRepository.findAll().isEmpty()) {
            initClientRepository();
        }
    }

    private void initRoomRepository() {
        Room room = new Room();
        room.setRoomNumber("403");
        room.setCost(new BigDecimal("110"));
        room.setBedsSingleCount(0);
        room.setBedsDoubleCount(1);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("404");
        room.setCost(new BigDecimal("85"));
        room.setBedsSingleCount(2);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("500A");
        room.setCost(new BigDecimal("150"));
        room.setBedsSingleCount(0);
        room.setBedsDoubleCount(2);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("500B");
        room.setCost(new BigDecimal("160"));
        room.setBedsSingleCount(2);
        room.setBedsDoubleCount(1);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("22");
        room.setCost(new BigDecimal("140"));
        room.setBedsSingleCount(4);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("23");
        room.setCost(new BigDecimal("140"));
        room.setBedsSingleCount(4);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("24");
        room.setCost(new BigDecimal("65"));
        room.setBedsSingleCount(1);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("25");
        room.setCost(new BigDecimal("65"));
        room.setBedsSingleCount(1);
        room.setBedsDoubleCount(0);
        roomRepository.saveAndFlush(room);

    }

    private void initClientRepository() {
        List<Room> rooms = roomRepository.findAll();
        List<Address> addresses = new ArrayList<Address>();
        List<Client> clients = new ArrayList<Client>();

        Address address = new Address();
        address.setCity("Siedlce");
        address.setPostcode("08-110");
        address.setProvince("Mazowieckie");
        address.setCountry("Polska");
        address.setStreetName("Kićka");
        address.setHouseNo("10");
        addressRepository.save(address);
        addresses.add(address);

        address = new Address();
        address.setCity("Warszawa");
        address.setPostcode("04-672");
        address.setProvince("Mazowieckie");
        address.setCountry("Polska");
        address.setStreetName("Nieistniejąca");
        address.setHouseNo("15B");
        addressRepository.save(address);

        for(int i = 0; i < 10; i++) {
            Client client = new Client();
            client.setFirstName("Client #" + i);
            client.setLastName("Kowalski");
            clientRepository.save(client);
            clients.add(client);
        }

        Reservation reservation = new Reservation();
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setNotes("Jeździ na wózku inwalidzkim.");
        reservation.setStartDate(LocalDate.of(2018, 1, 2));
        reservation.setEndDate(LocalDate.of(2018, 1, 4));
        reservation.setClient(clients.get(0));
        reservation.setAddress(address);
        reservation.setRoom(rooms.get(0));

        reservationRepository.save(reservation);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.FINISHED);
        reservation.setNotes("Jeździ na wózku inwalidzkim.");
        reservation.setStartDate(LocalDate.of(2018, 1, 19));
        reservation.setEndDate(LocalDate.of(2018, 2, 27));
        reservation.setClient(clients.get(1));
        reservation.setRoom(rooms.get(0));
        reservation.setAddress(addresses.get(0));
        reservationRepository.save(reservation);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setNotes("Ma uczulenie na pierze.");
        reservation.setStartDate(LocalDate.of(2018, 2, 9));
        reservation.setEndDate(LocalDate.of(2018, 2, 14));
        reservation.setClient(clients.get(0));
        reservation.setRoom(rooms.get(1));
        reservation.setAddress(address);
        reservationRepository.save(reservation);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.FINISHED);
        reservation.setNotes("Ma uczulenie na pierze.");
        reservation.setStartDate(LocalDate.of(2018, 1, 2));
        reservation.setEndDate(LocalDate.of(2018, 1, 6));
        reservation.setCheckInTime(LocalDateTime.of(2018, 1, 2, 13, 5));
        reservation.setCheckOutTime(LocalDateTime.of(2018, 1, 6, 11, 20));
        reservation.setClient(clients.get(0));
        reservation.setAddress(addresses.get(0));
        reservation.setRoom(rooms.get(2));
        reservationRepository.save(reservation);

        Payment payment = new Payment();
        payment.setDate(LocalDate.of(2018, 1, 2));
        payment.setAmount(new BigDecimal("65"));
        payment.setReservation(reservation);
        payment.setType(Payment.Type.CASH);
        paymentRepository.save(payment);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setNotes("Ma uczulenie na pierze.");
        reservation.setStartDate(LocalDate.of(2018, 1, 15));
        reservation.setEndDate(LocalDate.of(2018, 1, 19));
        reservation.setClient(clients.get(0));
        reservation.setRoom(rooms.get(2));
        reservation.setAddress(address);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.CHECK_OUT_OVERDUE);
        reservation.setNotes("");
        reservation.setStartDate(LocalDate.of(2018, 2, 5));
        reservation.setCheckInTime(LocalDateTime.of(2018, 2, 5, 14, 0));
        reservation.setEndDate(LocalDate.of(2018, 2, 7));
        reservation.setClient(clients.get(5));
        reservation.setRoom(rooms.get(5));
        reservation.setAddress(address);


        reservationRepository.save(reservation);
    }
}
