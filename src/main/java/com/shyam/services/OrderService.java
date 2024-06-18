package com.shyam.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shyam.config.custom.MyUserDetails;
import com.shyam.dto.MedicineOrderDTO;
import com.shyam.dto.OrderDTO;
import com.shyam.entities.MedicineEntity;
import com.shyam.entities.OrderEntity;
import com.shyam.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final MedicineService medicineService;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    public OrderEntity getMedicineById(int medicineId) {
        return orderRepository.findById(medicineId);
    }

    public OrderEntity saveOrder(OrderDTO orderDTO) {
        int id = ((MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        OrderEntity order = mapper.map(orderDTO, OrderEntity.class);
        MedicineEntity medicine = medicineService.getMedicine(orderDTO.getMedicineId());

        order.setId(0);
        order.setUserId(id);
        order.setStatus("placed");
        order.setTotalPrice(orderDTO.getQty() * medicine.getPrice());

        return orderRepository.save(order);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<MedicineOrderDTO> getUserOrders() {
        int id = ((MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return orderRepository.getUserMedicineOrder(id);
    }

}
