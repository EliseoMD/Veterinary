package com.veterinary.dto.request;

import java.sql.Timestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppointmentRequest {

    @NotBlank(message = "The appointment date is required")
    private Timestamp date;

    @NotBlank(message = "The reason is required")
    @Size(max = 100, message = "Reason must not exceed 100 characters")
    private String reason;

    @NotNull
    private Integer petId;

    @NotNull
    private Integer ownerId;

    @NotNull
    private Integer statusId;
}
