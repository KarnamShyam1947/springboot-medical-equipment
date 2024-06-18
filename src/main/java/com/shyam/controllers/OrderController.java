package com.shyam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shyam.dto.OrderDTO;
import com.shyam.services.MedicineService;
import com.shyam.services.OrderService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final MedicineService medicineService;

    @GetMapping("/")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getUserOrders());
        return "orders/home";
    }
    
    @GetMapping("/place-order/{medicineId}")
    public String placeOrder(
        @ModelAttribute("orderDTO") OrderDTO orderDTO,
        @PathVariable("medicineId") int medicineId,
        Model model
    ) {
        model.addAttribute("medicine", medicineId);
        model.addAttribute("medicineName", medicineService.getMedicine(medicineId).getName());
        return "orders/place-order";
    }
    
    @PostMapping("/place-order/")
    public String processPlaceOrder(
        @Valid @ModelAttribute("orderDTO") OrderDTO orderDTO,
        BindingResult result,
        HttpSession session
    ) {
        if (result.hasErrors()) {
            return "orders/place-order";
        }
        System.err.println(result);

        orderService.saveOrder(orderDTO);
        session.setAttribute("orderPlaced", "order successfully placed for your medicine");

        return "redirect:/orders/";
    }

}
