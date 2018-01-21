package com.teamknp.hotel.form;

import com.teamknp.hotel.entity.Reservation;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationEditForm {
    // reservation
    String notes;
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

    public static ReservationEditForm from(Reservation reservation) {
        ReservationEditForm f = new ReservationEditForm();
        f.setNotes(reservation.getNotes());
        f.setHouseNo(reservation.getAddress().getHouseNo());
        f.setPostcode(reservation.getAddress().getPostcode());
        f.setCity(reservation.getAddress().getCity());
        f.setStreet(reservation.getAddress().getStreet());
        f.setProvince(reservation.getAddress().getProvince());
        f.setCountry(reservation.getAddress().getCountry());

        f.setFirstName(reservation.getClient().getFirstName());
        f.setLastName(reservation.getClient().getLastName());

        return f;
    }
}
