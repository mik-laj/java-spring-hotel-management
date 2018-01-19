package com.teamknp.hotel.init;

import com.teamknp.hotel.entity.*;
import com.teamknp.hotel.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RepositoryMockupInitializer implements InitializingBean {
    private AddressRepository addressRepository;
    private ClientRepository clientRepository;
    private KeyStatusRepository keyStatusRepository;
    private PaymentRepository paymentRepository;
    private ReservationRepository reservationRepository;
    private ReservationRoomRepository reservationRoomRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    @Autowired
    public RepositoryMockupInitializer(AddressRepository addressRepository, ClientRepository clientRepository, KeyStatusRepository keyStatusRepository, PaymentRepository paymentRepository, ReservationRepository reservationRepository, ReservationRoomRepository reservationRoomRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.keyStatusRepository = keyStatusRepository;
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.reservationRoomRepository = reservationRoomRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void afterPropertiesSet() {
        if (roomRepository.findAll().isEmpty()) {
            initRoomRepository();
        }
        if (clientRepository.findAll().isEmpty()) {
            initClientRepository();
        }
    }

    private void initUserRepository() {
        //TODO:
    }

    private void initRoomRepository() {
        Room room = new Room();
        room.setRoomNumber("403");
        room.setCost(110);
        room.setBedsSingleCount(0);
        room.setBedsDoubleCount(1);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("404");
        room.setCost(85);
        room.setBedsSingleCount(2);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("500A");
        room.setCost(150);
        room.setBedsSingleCount(0);
        room.setBedsDoubleCount(2);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("500B");
        room.setCost(160);
        room.setBedsSingleCount(2);
        room.setBedsDoubleCount(1);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("22");
        room.setCost(140);
        room.setBedsSingleCount(4);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("23");
        room.setCost(140);
        room.setBedsSingleCount(4);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("24");
        room.setCost(65);
        room.setBedsSingleCount(1);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("25");
        room.setCost(65);
        room.setBedsSingleCount(1);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

    }

    private void initClientRepository() {
        //1
        Client client = new Client();
        client.setFirstName("Jan");
        client.setLastName("Kowalski");

        clientRepository.save(client);


        Reservation reservation = new Reservation();
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setNotes("Jeździ na wózku inwalidzkim.");
        reservation.setStartDate(LocalDate.of(2018, 1, 19));
        reservation.setEndDate(LocalDate.of(2018, 1, 27));
        reservation.setClient(client);

        Address address = new Address();
        address.setCity("Siedlce");
        address.setPostcode("08-110");
        address.setProvince("Mazowieckie");
        address.setCountry("Polska");
        address.setStreetName("Kićka");
        address.setHouseNo("10");

        addressRepository.save(address);
        reservation.setAddress(address);
        reservationRepository.save(reservation);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.CANCELLED);
        reservation.setNotes("Jeździ na wózku inwalidzkim.");
        reservation.setStartDate(LocalDate.of(2018, 1, 1));
        reservation.setEndDate(LocalDate.of(2018, 1, 4));
        reservation.setClient(client);

        addressRepository.save(address);
        reservation.setAddress(address);
        reservationRepository.save(reservation);

        //2
        client = new Client();
        client.setFirstName("Adrianna");
        client.setLastName("Oszust");

        clientRepository.save(client);
        addressRepository.save(address);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setNotes("Ma uczulenie na pierze.");
        reservation.setStartDate(LocalDate.of(2018, 1, 19));
        reservation.setEndDate(LocalDate.of(2018, 1, 27));
        reservation.setClient(client);

        address = new Address();
        address.setCity("Warszawa");
        address.setPostcode("04-672");
        address.setProvince("Mazowieckie");
        address.setCountry("Polska");
        address.setStreetName("Nieistniejąca");
        address.setHouseNo("15B");

        addressRepository.save(address);
        reservation.setAddress(address);
        reservationRepository.save(reservation);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.FINISHED);
        reservation.setNotes("Ma uczulenie na pierze.");
        reservation.setStartDate(LocalDate.of(2017, 12, 23));
        reservation.setEndDate(LocalDate.of(2017, 12, 25));
        reservation.setClient(client);

        reservation.setAddress(address);
        reservationRepository.save(reservation);

        Room room = new Room();
        room.setRoomNumber("26");
        room.setCost(65);
        room.setBedsSingleCount(1);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        Payment payment = new Payment();
        payment.setDate(LocalDate.of(2017, 12, 23));
        payment.setAmount(65);
        payment.setReservation(reservation);
        payment.setType(Payment.Type.CASH);
        paymentRepository.save(payment);

        ReservationRoom reservationRoom = new ReservationRoom();
        reservationRoom.setReservation(reservation);
        reservationRoom.setRoom(room);
        reservationRoomRepository.save(reservationRoom);

        KeyStatus keyStatus = new KeyStatus();
        keyStatus.setReservationRoom(reservationRoom);
        keyStatus.setTimeGiven(LocalDate.of(2017, 12, 23));
        keyStatus.setTimeReturned(LocalDate.of(2017, 12, 25));
        keyStatusRepository.save(keyStatus);

    }
}
