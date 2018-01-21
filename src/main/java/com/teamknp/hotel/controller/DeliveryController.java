package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Delivery;
import com.teamknp.hotel.entity.Product;
import com.teamknp.hotel.form.DeliveryForm;
import com.teamknp.hotel.form.DeliveryItemForm;
import com.teamknp.hotel.form.ProductForm;
import com.teamknp.hotel.services.DeliveryService;
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
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    String list(Model model, Pageable pageable) {
        model.addAttribute("entities", deliveryService.findAll(pageable));
        return "delivery/list";
    }

    @GetMapping("/{id}/")
    String view(
            @PathVariable("id") Delivery delivery,
            Model model
    ) {
        model.addAttribute("object", delivery);
        return "delivery/view";
    }

    @GetMapping("/add")
    String add(
            @ModelAttribute("formData") DeliveryForm formData
    ) {
        return "delivery/add";
    }

    @PostMapping("/add")
    String add(
            @ModelAttribute("formData") @Valid DeliveryForm formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "delivery/add";
        }
        Delivery entity = deliveryService.save(formData);
        return String.format("redirect:/delivery/%d/", entity.getId());
    }

    @GetMapping("/{id}/edit")
    String edit(
            @PathVariable("id") Delivery entity,
            Model model
    ) {
        model.addAttribute("formData", DeliveryForm.from(entity));
        model.addAttribute("object", entity);
        return "delivery/edit";
    }

    @PostMapping("/{id}/edit")
    String edit(
            @ModelAttribute("formData") DeliveryForm formData,
            BindingResult bindingResult,
            @PathVariable("id") Delivery entity,
            Model model
    ) {
        model.addAttribute("object", entity);
        if (bindingResult.hasErrors()) {
            return "delivery/edit";
        }
        deliveryService.update(entity, formData);
        return String.format("redirect:/admin/delivery/%d/", entity.getId());
    }

    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Delivery entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            deliveryService.delete(entity);
            // TODO: Add Toast
            return "redirect:/admin/delivery/";
        }
        model.addAttribute("object", entity);
        return "delivery/delete";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Delivery>> select2(
            @RequestParam("q") String search,
            Pageable pageable
    ) {
        Page<Delivery> vets = deliveryService.search(search, pageable);
        String idExpression = "#{id}";
        Select2DataSupport<Delivery> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }
}
