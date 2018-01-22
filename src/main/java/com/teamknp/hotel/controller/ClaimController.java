package com.teamknp.hotel.controller;

import com.teamknp.hotel.entity.Claim;
import com.teamknp.hotel.entity.Explanation;
import com.teamknp.hotel.form.ClaimForm;
import com.teamknp.hotel.form.ExplanationForm;
import com.teamknp.hotel.form.ResolveForm;
import com.teamknp.hotel.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/claim")
public class ClaimController {
    @Autowired
    ClaimService claimService;

    @Autowired
    ConversionService conversionService;

    @GetMapping("")
    String list(Model model, Pageable pageable) {
        model.addAttribute("entities", claimService.findAll(pageable));
        return "claim/list";
    }

    @GetMapping("/{id}/")
    @Secured("ROLE_WAREHOUSE")
    String view(
            @PathVariable("id") Claim claim,
            Model model
    ) {
        model.addAttribute("object", claim);
        model.addAttribute("explanationFormData", new ExplanationForm());
        return "claim/view";
    }

    @PostMapping("/{id}/add-explanation")
    String addExplanation(
            @PathVariable("id") Claim claim,
            @ModelAttribute("formData") @Valid ExplanationForm formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "claim/view";
        }
        Explanation entity = claimService.addExplanation(claim, formData);
        return String.format("redirect:/admin/claim/%d/", claim.getId());
    }

    @GetMapping("/add")
    String add(
            @ModelAttribute("formData") ClaimForm formData
    ) {
        return "claim/add";
    }

    @PostMapping("/add")
    String add(
            @ModelAttribute("formData") @Valid ClaimForm formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "claim/add";
        }
        Claim entity = claimService.addClaim(formData);
        return String.format("redirect:/admin/claim/%d/", entity.getId());
    }


    @GetMapping("/{id}/resolve")
    String resolve(
            Model model,
            @PathVariable("id") Claim claim,
            @ModelAttribute("formData") ResolveForm formData
    ) {
        model.addAttribute("object", claim);
        return "claim/resolve";
    }

    @PostMapping("/{id}/resolve")
    String resolve(
            @PathVariable("id") Claim claim,
            @ModelAttribute("formData") @Valid ResolveForm formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "claim/resolve";
        }
        claimService.resolveClaim(claim, formData);
        return String.format("redirect:/admin/claim/%d/", claim.getId());
    }


//    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
//    String delete(
//            HttpServletRequest request,
//            @PathVariable("id") Product entity,
//            Model model
//    ) {
//        boolean isPost = request.getMethod().equals("POST");
//        if (isPost) {
//            claimService.delete(entity);
//            // TODO: Add Toast
//            return "redirect:claim/";
//        }
//        model.addAttribute("object", entity);
//        return "claim/delete";
//    }

}
