package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Delivery;
import com.teamknp.hotel.entity.Role;
import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.form.DeliveryForm;
import com.teamknp.hotel.form.EmployeeForm;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @GetMapping("/add")
    String add(
            @ModelAttribute("formData") EmployeeForm formData
    ) {
        return "user/add";
    }

    @PostMapping("/add")
    String add(
            @ModelAttribute("formData") @Valid EmployeeForm formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "user/add";
        }
        User entity = userService.save(formData);
        return String.format("redirect:/admin/staff/%d/", entity.getId());
    }

    @GetMapping("/{id}/edit")
    String edit(
            @PathVariable("id") User entity,
            Model model
    ) {
        model.addAttribute("formData", new EmployeeForm());
        model.addAttribute("object", entity);
        return "user/edit";
    }

    @PostMapping("/{id}/edit")
    String edit(
            @ModelAttribute("formData") EmployeeForm formData,
            BindingResult bindingResult,
            @PathVariable("id") User entity,
            Model model
    ) {
        model.addAttribute("object", entity);
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(entity, formData);
        return String.format("redirect:/admin/staff/%d/", entity.getId());
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2-role")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Role>> select2Role(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<Role> vets = userService.searchRole(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<Role> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }

}
