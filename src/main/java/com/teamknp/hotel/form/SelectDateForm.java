package com.teamknp.hotel.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class SelectDateForm {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate start;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate end;
}
