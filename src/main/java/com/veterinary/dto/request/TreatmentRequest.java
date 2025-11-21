package com.veterinary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TreatmentRequest {
    @NotBlank(message = "The treatment name is required")
    @Size(max = 100)
    private String name;
    
    @NotBlank(message = "The description is required")
    @Size(max = 200)
    private String description;
    
    @NotNull(message = "The amount is required")
    private Double amount;
    
    @NotBlank(message = "The frequency is required")
    @Size(max = 50)
    private String frequency;
    
    @NotBlank(message = "The side effects are required")
    @Size(max = 100)
    private String sideEffects;

    private Boolean active;
}