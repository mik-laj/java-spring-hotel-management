package com.teamknp.hotel.validator;

import com.teamknp.hotel.form.PaymentForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class PaymentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PaymentForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PaymentForm paymentForm = (PaymentForm) o;

        if (paymentForm.getDate().isAfter(LocalDate.now())) {
            errors.rejectValue("date", "payment.invalid.date");
        }

    }
}