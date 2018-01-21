package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.Room;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class SelectRoomClientForm {
    // reservation
    String notes;
    Date start;
    Date end;
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
}
