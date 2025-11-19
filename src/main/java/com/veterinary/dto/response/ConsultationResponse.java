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
public class ConsultationResponse {

    @JsonProperty("Consultation ID")
    private Integer id;

    @JsonProperty("Appointment Type")
    private String type;

    @JsonProperty("Diagnosis")
    private String diagnosis;

    @JsonProperty("Consultation Date")
    private Timestamp date;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("Veterinarian Name")
    private VeterinarianResponse veterinarian;

}
