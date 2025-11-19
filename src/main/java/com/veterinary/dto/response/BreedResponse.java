package com.veterinary.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreedResponse {

    @JsonProperty("Breed ID")
    private Integer id;

    @JsonProperty("Breed Name")
    private String name;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("specie Name")
    private SpecieResponse specie;

}
