package com.shyam.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicineDTO {
    
    @NotBlank(message = "name is required")
    private String name;

    @Min(value = 1, message = "price must be grater than zero")
    private double price;

    @NotBlank(message = "description is required")
    private String description;

    private MultipartFile file;
}
