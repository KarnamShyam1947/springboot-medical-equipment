package com.shyam.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shyam.services.MedicineService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MedicineService medicineService;
    
    @GetMapping("/")
    public String idnex(Model model){
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "index";
    }
    
    @ResponseBody
    @GetMapping("/about")
    public Object about(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    @GetMapping("/test")
    public Object test(){
        return "test";
    }

}
