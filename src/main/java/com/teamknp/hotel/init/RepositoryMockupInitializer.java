package com.teamknp.hotel.init;

import com.teamknp.hotel.entity.*;
import com.teamknp.hotel.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class RepositoryMockupInitializer implements InitializingBean {
    private AddressRepository addressRepository;
    private ClientRepository clientRepository;
    private KeyStatusRepository keyStatusRepository;
    private PaymentRepository paymentRepository;
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    @Autowired
    public RepositoryMockupInitializer(AddressRepository addressRepository, ClientRepository clientRepository, KeyStatusRepository keyStatusRepository, PaymentRepository paymentRepository, ReservationRepository reservationRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.keyStatusRepository = keyStatusRepository;
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
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
        room.setCost(11000);
        room.setBedsSingleCount(0);
        room.setBedsDoubleCount(1);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("404");
        room.setCost(8500);
        room.setBedsSingleCount(2);
        room.setBedsDoubleCount(0);
        roomRepository.save(room);

        room = new Room();
        room.setRoomNumber("500A");
        room.setCost(15000);
        room.setBedsSingleCount(0);
        room.setBedsDoubleCount(2);
        roomRepository.save(room);
        roomRepository.flush();
    }

    private void initClientRepository() {
        List<Room> rooms = roomRepository.findAll();
        List<Address> addresses = new ArrayList<Address>();
        List<Client> clients = new ArrayList<Client>();

        Address address1 = new Address();
        address1.setCity("Siedlce");
        address1.setPostcode("08-110");
        address1.setProvince("Mazowieckie");
        address1.setCountry("Polska");
        address1.setStreetName("Kićka");
        address1.setHouseNo("10");
        addressRepository.save(address1);
        addresses.add(address1);

        Address address2 = new Address();
        address2.setCity("Warszawa");
        address2.setPostcode("04-672");
        address2.setProvince("Mazowieckie");
        address2.setCountry("Polska");
        address2.setStreetName("Nieistniejąca");
        address2.setHouseNo("15B");
        addressRepository.save(address2);

        for(int i = 0; i < 10; i++) {
            Client client = new Client();
            client.setFirstName("Client #" + i);
            client.setLastName("Kowalski");
            clientRepository.save(client);
            clients.add(client);
        }


        Reservation reservation1 = new Reservation();
        reservation1.setStatus(Reservation.Status.PENDING);
        reservation1.setNotes("Jeździ na wózku inwalidzkim.");
        reservation1.setStartDate(new GregorianCalendar(2018, 0, 2).getTime());
        reservation1.setEndDate(new GregorianCalendar(2018, 0, 4).getTime());
        reservation1.setClient(clients.get(0));
        reservation1.setAddress(address2);
        reservation1.setRoom(rooms.get(0));

        reservationRepository.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setStatus(Reservation.Status.CANCELLED);
        reservation2.setNotes("Jeździ na wózku inwalidzkim.");
        reservation2.setStartDate(new GregorianCalendar(2018, 0, 19).getTime());
        reservation2.setEndDate(new GregorianCalendar(2018, 1, 27).getTime());
        reservation2.setClient(clients.get(1));
        reservation2.setRoom(rooms.get(0));
        reservation2.setAddress(addresses.get(0));
        reservationRepository.save(reservation2);

        Reservation reservation = new Reservation();
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setNotes("Ma uczulenie na pierze.");
        reservation.setStartDate(new GregorianCalendar(2018, 0, 6).getTime());
        reservation.setEndDate(new GregorianCalendar(2018, 0, 14).getTime());
        reservation.setClient(clients.get(0));
        reservation.setRoom(rooms.get(1));
        reservation.setAddress(address2);

        reservationRepository.save(reservation);

        reservation = new Reservation();
        reservation.setStatus(Reservation.Status.FINISHED);
        reservation.setNotes("Ma uczulenie na pierze.");
        reservation.setStartDate(new GregorianCalendar(2018, 0, 2).getTime());
        reservation.setEndDate(new GregorianCalendar(2018, 0, 6).getTime());
        reservation.setClient(clients.get(0));
        reservation.setAddress(addresses.get(0));
        reservation.setRoom(rooms.get(2));
        reservationRepository.save(reservation);

        Reservation reservation3 = new Reservation();
        reservation3.setStatus(Reservation.Status.PENDING);
        reservation3.setNotes("Ma uczulenie na pierze.");
        reservation3.setStartDate(new GregorianCalendar(2018, 0, 15).getTime());
        reservation3.setEndDate(new GregorianCalendar(2018, 0, 19).getTime());
        reservation3.setClient(clients.get(0));
        reservation3.setRoom(rooms.get(2));
        reservation3.setAddress(address2);

        reservationRepository.save(reservation3);
    }


}
