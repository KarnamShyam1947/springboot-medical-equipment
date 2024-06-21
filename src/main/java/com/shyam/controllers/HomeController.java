package com.shyam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    
    @GetMapping("/about")
    public Object about(){
        return "pages/about";
    }
    
    @GetMapping("/contact")
    public Object contact(){
        return "pages/contact";
    }
    
    @GetMapping("/services")
    public Object services(){
        return "pages/services";
    }

    @GetMapping("/shop")
    public Object shop(Model model){
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "pages/shop";
    }
    
    @GetMapping("/test")
    public Object test(){
        return "test";
    }

    @GetMapping("/medicine-details/{medicineId}")
    public String singleProduct(
        @PathVariable("medicineId") int medicineId,
        Model model
    ) { 
        model.addAttribute("medicine", medicineService.getMedicine(medicineId));
        return "medicine/details";
    }

}
