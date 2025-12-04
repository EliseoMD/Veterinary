package com.veterinary.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AppointmentNoteRequest {

    @NotBlank(message = "The note name is required")
    @Size(max = 200)
    private String name;

    @NotBlank(message = "The note is required")
    @Size(max = 200)
    private String note;

    private LocalDateTime date;

    private Boolean active;

    @NotNull
    private Integer appointmentId;

}
