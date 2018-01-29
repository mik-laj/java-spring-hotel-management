package com.teamknp.hotel.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class InvoiceInfo {
    private final BigDecimal reservationInvoice;
    private final BigDecimal productInvoice;
    private final BigDecimal paymentSum;

    public InvoiceInfo(BigDecimal reservationInvoice, BigDecimal productInvoice, BigDecimal paymentSum) {
        this.reservationInvoice = reservationInvoice;
        this.productInvoice = productInvoice;
        this.paymentSum = paymentSum;
    }
}
