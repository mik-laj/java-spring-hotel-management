package com.teamknp.hotel.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class SelectDateForm {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date start;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date end;
}
