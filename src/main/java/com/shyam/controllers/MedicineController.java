package com.shyam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shyam.dto.MedicineDTO;
import com.shyam.services.MedicineService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping("/add")
    public String addMedicine(@ModelAttribute("medicineDTO") MedicineDTO medicineDTO) {
        return "medicine/add-medicine";
    }
    
    @PostMapping("/add")
    public String processAddMedicine(
        @Valid @ModelAttribute("medicineDTO") MedicineDTO medicineDTO,
        BindingResult result,
        HttpSession session
    ) {
        if (result.hasErrors()) 
            return "medicine/add-medicine";
        
        if (medicineDTO.getFile() != null && medicineDTO.getFile().isEmpty()) {
            result.rejectValue("file", "error.file", "please select file");
            return "medicine/add-medicine";
        }
        
        if (medicineService.getMedicine(medicineDTO.getName()) != null) {
            result.rejectValue("name", "error.name", "medicine already added in store");
            return "medicine/add-medicine";
        }

        session.setAttribute("medicineAdded", "New Medicine added sucessfully");
        medicineService.saveMedicine(medicineDTO);
        return "redirect:/";
    }
}
