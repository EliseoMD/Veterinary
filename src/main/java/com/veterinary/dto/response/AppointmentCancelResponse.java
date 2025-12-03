package com.veterinary.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCancelResponse {

    @JsonProperty("Appointment Cancel ID")
    private Integer id;

    @JsonProperty("Cancel Date")
    private LocalDateTime cancelDate;

    @JsonProperty("Reason")
    private String reason;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("Appointment ID")
    private Integer appointmentId;
}
