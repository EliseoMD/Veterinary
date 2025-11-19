package com.veterinary.mapper;

import com.veterinary.dto.request.ConsultationRequest;
import com.veterinary.dto.response.ConsultationResponse;
import com.veterinary.model.Consultation;
import com.veterinary.model.Veterinarian;

public final class ConsultationMapper {

    public static ConsultationResponse toResponse(Consultation consultation) {
        if (consultation == null) {
            return null;
        }
        return ConsultationResponse.builder()
                .id(consultation.getId())
                .type(consultation.getType())
                .diagnosis(consultation.getDiagnosis())
                .date(consultation.getDate())
                .veterinarian(VeterinarianMapper.toResponse(consultation.getVeterinarian()))
                .build();
    }

    public static Consultation toEntity(ConsultationRequest dto, Veterinarian veterinarian) {
        if (dto == null) {
            return null;
        }
        return Consultation.builder()
                .type(dto.getType())
                .diagnosis(dto.getDiagnosis())
                .date(dto.getDate())
                .veterinarian(veterinarian)
                .build();
    }

    public static void copyToEntity(ConsultationRequest dto, Consultation entity, Veterinarian veterinarian) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setType(dto.getType());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setDate(dto.getDate());
        entity.setVeterinarian(veterinarian);
    }
}
