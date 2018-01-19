package com.teamknp.hotel.controller;


import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.form.ReservationForm;
import com.teamknp.hotel.services.ReservationService;
import com.teamknp.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ModelAttribute("/admin/reservation/add")
    public List<Room> roomList() {
        return ReservationService.getAllRooms();
    }


    @GetMapping("/admin/reservation/show")
    public String list(Model model) {
        model.addAttribute("reservationsList", ReservationService.findAll());
        return "reservation/show";
    }

}
