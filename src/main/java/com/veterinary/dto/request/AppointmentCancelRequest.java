package com.veterinary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppointmentCancelRequest {

    @NotNull(message = "The appointment ID is required")
    private Integer appointmentId;

    @NotBlank(message = "The reason is required")
    @Size(max = 200)
    private String reason;

    private Boolean active;
}
