package com.veterinary.dto.request;

import java.sql.Timestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConsultationRequest {

    @NotBlank(message = "The appointment type is required")
    @Size(max = 20)
    private String type;

    @NotBlank(message = "The diagnosis is required")
    @Size(max = 200)
    private String diagnosis;

    @NotNull(message = "The consultation date is required")
    private Timestamp date;

    @NotNull
    private Integer veterinarianId;
}
