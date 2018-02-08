package com.teamknp.hotel.domain;

import com.teamknp.hotel.entity.Payment;
import com.teamknp.hotel.entity.SoldItem;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Invoice {
    private final BigDecimal reservationInvoice;
    private final BigDecimal productInvoice;
    private final BigDecimal paymentSum;
    private final List<Payment> payments;
    private final List<SoldItem> soldItems;

    public Invoice(BigDecimal reservationInvoice, BigDecimal productInvoice, BigDecimal paymentSum, List<Payment> payments, List<SoldItem> soldItems) {
        this.reservationInvoice = reservationInvoice;
        this.productInvoice = productInvoice;
        this.paymentSum = paymentSum;
        this.payments = payments;
        this.soldItems = soldItems;
    }
}
