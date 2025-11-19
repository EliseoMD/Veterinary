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
public class VeterinarianResponse {
    @JsonProperty("Veterinarian ID")
    private Integer id;

    @JsonProperty("Veterinarian Name")
    private String name;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Active")
    private Boolean active;
}
