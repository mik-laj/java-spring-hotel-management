package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.services.ReservationService;
import io.springlets.data.web.select2.Select2DataSupport;
import io.springlets.data.web.select2.Select2DataWithConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
@RequestMapping("/admin/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    String list(Model model, Pageable pageable) {
        model.addAttribute("entities", reservationService.findAll(pageable));
        return "reservation/list";
    }

    @GetMapping("/{id}/")
    String view(
            @PathVariable("id") Reservation reservation,
            Model model
    ) {
        model.addAttribute("object", reservation);
        return "reservation/view";
    }

//    @GetMapping("/add")
//    String add(
////            @ModelAttribute("formData") ReservationFormData formData
//    ) {
//        return "reservation/add";
//    }
//
//    @PostMapping("/add")
//    String add(
////            @ModelAttribute("formData") @Valid ReservationFormData formData,
//            BindingResult bindingResult
//    ) {
//        if (bindingResult.hasErrors()) {
//            return "admin/reservation/add";
//        }
//        Reservation reservation = new Reservation();
////        formData.patch(reservation);
////        cityManager.save(reservation);
//        return String.format("redirect:/admin/reservation/%d/", reservation.getId());
//    }


    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Reservation entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            reservationService.delete(entity);
            // TODO: Add Toast
            return "redirect:/admin/reservation/";
        }
        model.addAttribute("object", entity);
        return "reservation/delete";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Reservation>> select2(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<Reservation> vets = reservationService.search(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<Reservation> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }
}