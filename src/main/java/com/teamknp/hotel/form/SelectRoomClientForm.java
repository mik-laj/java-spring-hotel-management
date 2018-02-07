package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.Reservation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SelectRoomClientForm {
    // reservation
    String notes;
    LocalDate start;
    LocalDate end;
    Long roomId;
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

    public static SelectRoomClientForm from(Reservation reservation) {
        SelectRoomClientForm f = new SelectRoomClientForm();
        return f;
    }

    public Long getRoom() {
        return roomId;
    }
}
