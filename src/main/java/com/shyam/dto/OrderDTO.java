package com.shyam.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

    @Min(value = 1, message = "quntity must be grater than 0")
    private int qty;

    @NotBlank(message = "phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Please enter a valid phone number")
    private String phone;

    @NotBlank(message = "address is required")
    private String address;
    
    private int medicineId;
}
