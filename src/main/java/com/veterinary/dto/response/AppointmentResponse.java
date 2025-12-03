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
public class AppointmentResponse {

    @JsonProperty("Appointment ID")
    private Integer id;

    @JsonProperty("Appointment Date")
    private LocalDateTime date;

    @JsonProperty("Reason")
    private String reason;

    @JsonProperty("Created At")
    private LocalDateTime createdAt;

    @JsonProperty("Pet Name")
    private Integer pet;

    @JsonProperty("Owner Name")
    private Integer owner;

    @JsonProperty("Status Name")
    private AppointmentStatusResponse status;

    @JsonProperty("Active")
    private Boolean active;

}
