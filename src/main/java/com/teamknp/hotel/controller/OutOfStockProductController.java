package com.teamknp.hotel.controller;

import com.teamknp.hotel.repository.ProductRepository;
import com.teamknp.hotel.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OutOfStockProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/admin/product/out-of-stock")
    public String outOfStock(Model model) {
        return "product/out-of-stock-form";
    }

    @PostMapping("/admin/product/out-of-stock")
    public String outOfStockConfirmed(Model model) {
        model.addAttribute("entities", productService.getOutOfStockProducts());
        return "product/out-of-stock-list";
    }

}
