package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Payment;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.PaymentForm;
import com.teamknp.hotel.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> findAll(Sort sort) {
        return paymentRepository.findAll(sort);
    }

    public List<Payment> findAll(Iterable<Long> iterable) {
        return paymentRepository.findAll(iterable);
    }

    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    public long count() {
        return paymentRepository.count();
    }

    public void delete(Payment payment) {
        paymentRepository.delete(payment);
    }

    public BigDecimal sumPaymentsForReservation(Reservation reservation) {
    BigDecimal result = paymentRepository.getSumByReservationId(reservation.getId());
        if (result == null) {
            result = new BigDecimal("0");
        }
        return result;
    }

    public void addPayment(Reservation entity, PaymentForm formData) {
        Payment payment = new Payment();
        payment.setReservation(entity);
        payment.setDate(formData.getDate());
        payment.setAmount(formData.getAmount());
        payment.setType(Payment.Type.valueOf(formData.getType()));
        paymentRepository.save(payment);
    }

    /*
    public InvoiceInfo getInvoice(Reservation reservation) {
        InvoiceInfo invoiceInfo = new InvoiceInfo(
                reservationService.getReservationCost(reservation),
                saleService.getTotalValue(soldItems),
                paymentService.sumPaymentsForReservation(reservation.getId()));
    }
    */
}
