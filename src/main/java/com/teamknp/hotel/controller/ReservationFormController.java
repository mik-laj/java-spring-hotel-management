package com.teamknp.hotel.controller;


import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.ReservationForm;
import com.teamknp.hotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ReservationFormController {
    private ReservationService ReservationService;

    @Autowired
    public ReservationFormController(ReservationService ReservationService)
    {
        this.ReservationService = ReservationService;
    }

    @RequestMapping(value="/admin/reservation/add", method= RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("formData", new ReservationForm());

        return "reservation/add";
    }

    @RequestMapping(value="/admin/reservation/add", method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("formData") ReservationForm r, BindingResult errors){

        if(errors.hasErrors()){
            return "reservation/add";
        }

        Reservation reservation = ReservationService.saveNewReservation(r);

        return "redirect:/admin/reservation/" + reservation.getId();
    }



}
