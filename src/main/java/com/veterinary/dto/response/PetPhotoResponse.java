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
public class PetPhotoResponse {

    @JsonProperty("Photo ID")
    private Integer id;

    @JsonProperty("Pet ID")
    private Integer petId;

    @JsonProperty("URL")
    private String url;

    @JsonProperty("Upload Date")
    private LocalDateTime uploadDate;
}
