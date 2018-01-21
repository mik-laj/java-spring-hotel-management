package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.services.RoomService;
import com.teamknp.hotel.services.UserService;
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
@RequestMapping("/admin/staff")
public class UsersController {
    @Autowired
    UserService userService;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    @Secured("ROLE_HR")
    String list(Model model, Pageable pageable) {
        model.addAttribute("entities", userService.findAll(pageable));
        return "user/list";
    }

    @GetMapping("/{id}/")
    @Secured("ROLE_HR")
    String view(
            @PathVariable("id") User user,
            Model model
    ) {
        model.addAttribute("object", user);
        return "user/view";
    }

    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @Secured("ROLE_HR")
    String delete(
            HttpServletRequest request,
            @PathVariable("id") User entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            userService.delete(entity);
            // TODO: Add Toast
            return "redirect:/admin/user/";
        }
        model.addAttribute("object", entity);
        return "user/delete";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<User>> select2(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<User> vets = userService.search(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<User> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }
}
