package com.teamknp.hotel.services;

import com.teamknp.hotel.domain.InvoiceInfo;
import com.teamknp.hotel.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    ReservationService reservationService;

    @Autowired
    SaleService saleService;

    @Autowired
    PaymentService paymentService;

    public InvoiceInfo getInvoice(Reservation reservation) {
        return new InvoiceInfo(
                reservationService.getReservationCost(reservation),
                saleService.getTotalValue(saleService.findAllByReservation(reservation)),
                paymentService.sumPaymentsForReservation(reservation)
        );
    }
}
