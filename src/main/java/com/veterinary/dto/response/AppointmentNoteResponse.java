package com.veterinary.dto.response;

import java.sql.Timestamp;
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
public class AppointmentNoteResponse {

    @JsonProperty("Note ID")
    private Integer id;

    @JsonProperty("Note Name")
    private String name;

    @JsonProperty("Note Content")
    private String note;

    @JsonProperty("Note Date")
    private LocalDateTime date;

    @JsonProperty("Appointment Reason")
    private String reason;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("Appointment")
    private AppointmentResponse appointment;
}
