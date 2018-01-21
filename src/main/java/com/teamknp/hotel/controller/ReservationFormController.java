package com.teamknp.hotel.controller;


import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.form.SelectDateForm;
import com.teamknp.hotel.form.SelectRoomClientForm;
import com.teamknp.hotel.services.ReservationService;
import com.teamknp.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class ReservationFormController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(String.class, "roomId", new CustomNumberEditor(Long.class, false));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @RequestMapping(value="/admin/reservation/select-date", method= RequestMethod.GET)
    public String selectDate(Model model){
        model.addAttribute("formData", new SelectDateForm());

        return "reservation/select-date";
    }
    @RequestMapping(value="/admin/reservation/select-room-client", method= RequestMethod.POST)
    public String selectRoomClient(@Valid SelectDateForm selectDateForm, Model model, BindingResult errors){
        if(errors.hasErrors()){
            model.addAttribute("formData", selectDateForm);
            return "reservation/select-date";
        }
        SelectRoomClientForm formData = new SelectRoomClientForm();
        LocalDate start = selectDateForm.getStart();
        LocalDate end = selectDateForm.getEnd();
        List<Room> avialableRooms = roomService.findAvailableRoomByDate(start, end);
        formData.setStart(start);
        formData.setEnd(end);
        model.addAttribute("avialableRooms", avialableRooms);
        model.addAttribute("formData", formData);
        return "reservation/select-room-client";
    }

    @RequestMapping(value="/admin/reservation/save-room-client", method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("formData") SelectRoomClientForm formData, BindingResult errors){
        if(errors.hasErrors()){
            return "reservation/select-room-client";
        }
        Reservation reservation = reservationService.saveNewReservation(formData);

        return "redirect:/admin/reservation/" + reservation.getId();
    }

}
