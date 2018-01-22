package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Client;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.services.ClientService;
import io.springlets.data.web.select2.Select2DataSupport;
import io.springlets.data.web.select2.Select2DataWithConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    @Secured("ROLE_RECEPTION")
    String list(Model model, Pageable pageable) {
        model.addAttribute("entities", clientService.findAll(pageable));
        return "client/list";
    }

    @GetMapping("/{id}/")
    @Secured("ROLE_RECEPTION")
    String view(
            @PathVariable("id") Client client,
            Model model
    ) {
        model.addAttribute("object", client);
        return "client/view";
    }

//    @GetMapping("/add")
//    String add(
////            @ModelAttribute("formData") ReservationFormData formData
//    ) {
//        return "client/add";
//    }
//
//    @PostMapping("/add")
//    String add(
////            @ModelAttribute("formData") @Valid ReservationFormData formData,
//            BindingResult bindingResult
//    ) {
//        if (bindingResult.hasErrors()) {
//            return "admin/client/add";
//        }
//        Client client = new Client();
////        formData.patch(client);
////        cityManager.addClaim(client);
//        return String.format("redirect:/admin/client/%d/", client.getId());
//    }


    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @Secured("ROLE_RECEPTION")
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Client entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            clientService.delete(entity);
            // TODO: Add Toast
            return "redirect:/admin/client/";
        }
        model.addAttribute("object", entity);
        return "client/delete";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Reservation>> select2(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<Reservation> vets = clientService.search(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<Reservation> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }
}