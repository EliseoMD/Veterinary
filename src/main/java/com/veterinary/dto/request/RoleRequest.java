package com.veterinary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequest {

    @NotBlank(message = "The role name is required")
    @Size(max = 50)
    private String name;

    private Boolean active;
}
