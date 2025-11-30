package com.veterinary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    //@Email(message = "Must be a valid email address")
    @NotBlank(message = "The email is required")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "The password is required")
    @Size(max = 50)
    private String password;

    private Boolean active;

    @NotNull
    private Integer roleId;
}
