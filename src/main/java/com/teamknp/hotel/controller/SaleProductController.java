package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.SaleProductForm;
import com.teamknp.hotel.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaleProductController {

    @Autowired
    SaleService saleService;

    @GetMapping("admin/reservation/{id}/sell-product")
    String sale(
            @PathVariable("id") Reservation entity,
            Model model
    ) {
        model.addAttribute("formData", new SaleProductForm());
        model.addAttribute("object", entity);
        return "sale/sell-product";
    }

    @PostMapping("admin/reservation/{id}/sell-product")
    String sale(
            @ModelAttribute("formData") SaleProductForm formData,
            BindingResult bindingResult,
            @PathVariable("id") Reservation entity,
            Model model
    ) {
        model.addAttribute("object", entity);
        if (bindingResult.hasErrors()) {
            return "sale/sell-product";
        }
        saleService.sell(entity, formData);
        return String.format("redirect:/admin/reservation/%d/", entity.getId());
    }
}
