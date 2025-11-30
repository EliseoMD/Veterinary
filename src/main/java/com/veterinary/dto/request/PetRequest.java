package com.veterinary.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PetRequest {

    @NotBlank(message = "The pet name is required")
    @Size(max = 50)
    private String name;

    @NotNull(message = "The birth date is required")
    private LocalDate birthDate;

    @NotNull(message = "The age is required")
    private Integer age;

    @NotNull
    private Integer ownerId;

    @NotNull
    private Integer specieId;

    @NotNull
    private Integer breedId;
}
