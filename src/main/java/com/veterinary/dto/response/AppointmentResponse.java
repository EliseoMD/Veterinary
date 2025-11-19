package com.veterinary.dto.response;

import java.sql.Timestamp;
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
    private Timestamp date;

    @JsonProperty("Reason")
    private String reason;

    @JsonProperty("Created At")
    private Timestamp createdAt;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("Pet Name")
    private PetResponse pet;

    @JsonProperty("Owner Name")
    private OwnerResponse owner;

    @JsonProperty("Status Name")
    private AppointmentStatusResponse status;

}
