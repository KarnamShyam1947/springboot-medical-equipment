package com.shyam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineOrderDTO {
    private int medicineId;
    private String medicineName;
    private double medicinePrice;
    private int orderQty;
    private double totalPrice;
    private String orderStatus;
    private int orderId;
    private String orderAddress;
    private String orderPhone;
}
