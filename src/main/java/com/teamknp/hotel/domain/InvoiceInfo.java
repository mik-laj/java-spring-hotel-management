package com.teamknp.hotel.domain;

import io.springlets.format.EntityFormat;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EntityFormat("#{invoice} PLN #{paymentSum} PLN")
public class InvoiceInfo {
    private final BigDecimal invoice;
    private final BigDecimal paymentSum;

    public InvoiceInfo(BigDecimal invoice, BigDecimal paymentSum) {
        this.invoice = invoice;
        this.paymentSum = paymentSum;
    }
}
