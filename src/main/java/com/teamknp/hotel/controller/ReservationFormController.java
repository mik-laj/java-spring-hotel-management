package com.teamknp.hotel.controller;


import com.teamknp.hotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class ReservationFormController {
    private ReservationService ReservationService;

    @Autowired
    public ReservationFormController(ReservationService ReservationService)
    {
        this.ReservationService = ReservationService;
    }

    @RequestMapping(value="/reservationForm.html", method= RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("formData", new Command());

        return "reservationForm";
    }

    @RequestMapping(value="/reservationForm.html", method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("formData") Command r, BindingResult errors){

        if(errors.hasErrors()){
            return "reservationForm";
        }

        ReservationService.saveNewReservation(r);

        return "redirect:home.html";//na razie przekierowanie na  glowna
    }



}
