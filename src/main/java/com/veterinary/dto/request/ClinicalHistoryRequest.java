package com.veterinary.dto.request;

import java.sql.Date;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClinicalHistoryRequest {

    @NotNull(message = "The history date is required")
    private Date date;

    @NotNull
    private Integer petId;

    @NotNull
    private Integer consultationId;

    @NotNull
    private Integer treatmentId;
}
