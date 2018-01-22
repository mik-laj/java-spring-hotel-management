package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.services.RoomService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    @Secured("ROLE_RECEPTION")
    String list(Model model, Pageable pageable) {
        model.addAttribute("entities", roomService.findAll(pageable));
        return "room/list";
    }

    @GetMapping("/{id}/")
    @Secured("ROLE_RECEPTION")
    String view(
            @PathVariable("id") Room room,
            Model model
    ) {
        model.addAttribute("object", room);
        return "room/view";
    }

//    @GetMapping("/add")
//    String add(
////            @ModelAttribute("formData") RoomFormData formData
//    ) {
//        return "room/add";
//    }
//
//    @PostMapping("/add")
//    String add(
////            @ModelAttribute("formData") @Valid RoomFormData formData,
//            BindingResult bindingResult
//    ) {
//        if (bindingResult.hasErrors()) {
//            return "admin/room/add";
//        }
//        Room room = new Room();
////        formData.patch(room);
////        cityManager.addClaim(city);
//        return String.format("redirect:/admin/room/%d/", room.getId());
//    }


    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @Secured("ROLE_RECEPTION")
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Room entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            roomService.delete(entity);
            // TODO: Add Toast
            return "redirect:/admin/room/";
        }
        model.addAttribute("object", entity);
        return "room/delete";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    @Secured("ROLE_RECEPTION")
    public ResponseEntity<Select2DataSupport<Room>> select2(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<Room> vets = roomService.search(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<Room> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }
}
