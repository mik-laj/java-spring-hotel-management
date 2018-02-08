package com.teamknp.hotel.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentForm {
    @NotNull
    @Digits(integer=5, fraction=2)
    BigDecimal amount;
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    LocalDate date;
    @NotNull
    String type;

}
