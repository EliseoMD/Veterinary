package com.veterinary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Accessors(chain = true)
public class LoginResponse {

    private String token;
    private long expiresIn;
}
