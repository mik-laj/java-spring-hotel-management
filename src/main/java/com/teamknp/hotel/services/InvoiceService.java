package com.teamknp.hotel.services;

import com.teamknp.hotel.domain.Invoice;
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

    public Invoice getInvoice(Reservation reservation) {
        return new Invoice(
                reservationService.getReservationCost(reservation),
                saleService.getTotalValue(saleService.findAllByReservation(reservation)),
                paymentService.sumPaymentsForReservation(reservation),
                paymentService.findAllByReservation(reservation),
                saleService.findAllByReservation(reservation)
        );
    }
}
