package com.veterinary.mapper;

import com.veterinary.dto.request.TreatmentRequest;
import com.veterinary.dto.response.TreatmentResponse;
import com.veterinary.model.Treatment;

public final class TreatmentMapper {

    public static TreatmentResponse toResponse(Treatment treatment) {
        if (treatment == null)
            return null;
        return TreatmentResponse.builder()
                .id(treatment.getId())
                .name(treatment.getName())
                .description(treatment.getDescription())
                .amount(treatment.getAmount())
                .frequency(treatment.getFrequency())
                .sideEffects(treatment.getSideEffects())
                .build();
    }

    public static Treatment toEntity(TreatmentRequest dto) {
        if (dto == null)
            return null;
        return Treatment.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .frequency(dto.getFrequency())
                .sideEffects(dto.getSideEffects())
                .build();
    }

    public static void copyToEntity(TreatmentRequest dto, Treatment entity) {
        if (dto == null || entity == null)
            return;
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setFrequency(dto.getFrequency());
        entity.setSideEffects(dto.getSideEffects());
    }
}