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
public class RoleResponse {

    @JsonProperty("Role ID")
    private Integer id;

    @JsonProperty("Role Name")
    private String name;

    @JsonProperty("Active")
    private Boolean active;
}
