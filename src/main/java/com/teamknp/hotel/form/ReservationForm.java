package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.Room;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationForm {
    // reservation
    String notes;
    LocalDate start;
    LocalDate end;
    String room;
    // address
    String houseNo;
    String postcode;
    String city;
    String street;
    String province;
    String country;

    // Client
    String firstName;
    String lastName;
    //Room
    List<Room> roomNO;
}
