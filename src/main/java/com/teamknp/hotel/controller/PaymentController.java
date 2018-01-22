package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.PaymentForm;
import com.teamknp.hotel.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Secured("ROLE_RECEPTION")
    @GetMapping("admin/reservation/{id}/add-payment")
    String addPayment(
            @PathVariable("id") Reservation entity,
            Model model
    ) {
        model.addAttribute("formData", new PaymentForm());
        model.addAttribute("object", entity);
        return "payment/add";
    }

    @Secured("ROLE_RECEPTION")
    @PostMapping("admin/reservation/{id}/add-payment")
    String addPayment(
            @ModelAttribute("formData") PaymentForm formData,
            BindingResult bindingResult,
            @PathVariable("id") Reservation entity,
            Model model
    ) {
        model.addAttribute("object", entity);
        if (bindingResult.hasErrors()) {
            return "payment/add";
        }
        paymentService.addPayment(entity, formData);
        return String.format("redirect:/admin/reservation/%d/", entity.getId());
    }
}
