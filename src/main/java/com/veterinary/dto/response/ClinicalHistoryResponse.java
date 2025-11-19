package com.veterinary.dto.response;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalHistoryResponse {

    @JsonProperty("History ID")
    private Integer id;

    @JsonProperty("History Date")
    private Date date;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("Pet Name")
    private PetResponse pet;

    @JsonProperty("Consultation Date")
    private ConsultationResponse consultation;

    @JsonProperty("Treatment Name")
    private TreatmentResponse treatment;

}
