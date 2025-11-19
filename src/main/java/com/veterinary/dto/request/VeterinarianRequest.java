package com.veterinary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VeterinarianRequest {
    @NotBlank(message = "The veterinarian name is required")
    @Size(max = 100)
    private String name;
    
    @NotBlank(message = "The phone is required")
    @Size(max = 10)
    @Pattern(regexp = "^[0-9]+$", message = "Phone must contain only numbers")
    private String phone;
    
    @NotBlank(message = "The email is required")
    @Email(message = "Must be a valid email address")
    @Size(max = 100)
    private String email;

    private Boolean active;
}