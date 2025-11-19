package com.veterinary.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BreedRequest {

    @NotBlank(message = "The breed name is required")
    @Size(max = 50)
    private String name;

    @NotNull(message = "The specie ID is required")
    @JsonProperty("Species ID")
    private Integer specieId;
}
