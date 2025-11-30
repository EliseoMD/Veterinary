package com.veterinary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PetPhotoRequest {

    @NotNull(message = "The pet ID is required")
    private Integer petId;

    @NotBlank(message = "The URL is required")
    @Size(max = 500)
    private String url;
}
