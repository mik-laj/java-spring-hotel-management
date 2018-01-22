package com.teamknp.hotel.form;

import lombok.Data;

@Data
public class ClaimForm {
    String firstName;
    String lastName;
    Long reservation;
    String phone;
    String content;
}
