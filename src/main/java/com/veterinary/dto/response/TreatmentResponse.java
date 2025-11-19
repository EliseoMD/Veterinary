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
public class TreatmentResponse {

    @JsonProperty("Treatment Id")
    private Integer id;

    @JsonProperty("Treatment Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Amount")
    private Double amount;

    @JsonProperty("Frequency")
    private String frequency;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("Side Effects")
    private String sideEffects;
}
