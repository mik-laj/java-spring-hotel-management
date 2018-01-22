package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Product;
import com.teamknp.hotel.entity.Room;
import com.teamknp.hotel.form.ProductForm;
import com.teamknp.hotel.repository.ProductRepository;
import com.teamknp.hotel.services.ProductService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    @Secured("ROLE_WAREHOUSE")
    String list(Model model, Pageable pageable) {
        model.addAttribute("entities", productService.findAll(pageable));
        return "product/list";
    }

    @GetMapping("/{id}/")
    @Secured("ROLE_WAREHOUSE")
    String view(
            @PathVariable("id") Product product,
            Model model
    ) {
        model.addAttribute("object", product);
        return "product/view";
    }

    @GetMapping("/add")
    @Secured("ROLE_WAREHOUSE")
    String add(
            @ModelAttribute("formData") ProductForm formData
    ) {
        return "product/add";
    }

    @PostMapping("/add")
    @Secured("ROLE_WAREHOUSE")
    String add(
            @ModelAttribute("formData") @Valid ProductForm formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "product/add";
        }
        Product entity = productService.save(formData);
        return String.format("redirect:/admin/product/%d/", entity.getId());
    }


    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @Secured("ROLE_WAREHOUSE")
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Product entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            productService.delete(entity);
            // TODO: Add Toast
            return "redirect:product/";
        }
        model.addAttribute("object", entity);
        return "product/delete";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "select2", value = "/s2")
    @ResponseBody
    public ResponseEntity<Select2DataSupport<Product>> select2(
            @RequestParam("q") String search,
            @RequestParam(value = "only_available", required = false, defaultValue = "false") String only_available_param,
            Pageable pageable
    ) {
        boolean only_available = "true".equals(only_available_param);
        Page<Product> vets = productService.search(search, pageable, only_available);
        String idExpression = "#{id}";
        Select2DataSupport<Product> select2Data = new Select2DataWithConversion<>(vets, idExpression, conversionService);
        return ResponseEntity.ok(select2Data);
    }
}
