package com.veterinary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnerRequest {
    @NotBlank(message = "The owner name is required")
    @Size(max = 100)
    private String name;
    
    @NotBlank(message = "The phone is required")
    @Size(max = 10, message = "Phone must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Phone must contain only numbers")
    private String phone;
    
    @NotBlank(message = "The address is required")
    @Size(max = 200)
    private String address;
}