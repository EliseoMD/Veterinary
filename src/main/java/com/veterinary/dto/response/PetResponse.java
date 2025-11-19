package com.veterinary.dto.response;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {

    @JsonProperty("Pet ID")
    private Integer id;

    @JsonProperty("Pet Name")
    private String name;

    @JsonProperty("Birth Date")
    private Date birthDate;

    @JsonProperty("Age")
    private Integer age;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("Owner Name")
    private OwnerResponse owner;

    @JsonProperty("Specie Name")
    private SpecieResponse specie;

    @JsonProperty("Breed Name")
    private BreedResponse breed;

}
