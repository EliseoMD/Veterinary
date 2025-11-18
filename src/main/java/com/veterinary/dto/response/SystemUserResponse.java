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
public class SystemUserResponse {

    @JsonProperty("User ID")
    private Integer id;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Role")
    private SystemRoleResponse role;

    @JsonProperty("Active")
    private Boolean active;
}
