package com.veterinary.mapper;

import com.veterinary.dto.request.ClinicalHistoryRequest;
import com.veterinary.dto.response.ClinicalHistoryResponse;
import com.veterinary.model.ClinicalHistory;
import com.veterinary.model.Consultation;
import com.veterinary.model.Pet;
import com.veterinary.model.Treatment;

public final class ClinicalHistoryMapper {

    public static ClinicalHistoryResponse toResponse(ClinicalHistory clinicalHistory) {
        if (clinicalHistory == null) {
            return null;
        }
        return ClinicalHistoryResponse.builder()
                .id(clinicalHistory.getId())
                .date(clinicalHistory.getDate())
                .pet(PetMapper.toResponse(clinicalHistory.getPet()))
                .consultation(ConsultationMapper.toResponse(clinicalHistory.getConsultation()))
                .treatment(TreatmentMapper.toResponse(clinicalHistory.getTreatment()))
                .build();
    }

    public static ClinicalHistory toEntity(ClinicalHistoryRequest dto, Pet pet, Consultation consultation, Treatment treatment) {
        if (dto == null) {
            return null;
        }
        return ClinicalHistory.builder()
                .date(dto.getDate())
                .pet(pet)
                .consultation(consultation)
                .treatment(treatment)
                .build();
    }

    public static void copyToEntity(ClinicalHistoryRequest dto, ClinicalHistory entity, Pet pet, Consultation consultation, Treatment treatment) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setDate(dto.getDate());
        entity.setPet(pet);
        entity.setConsultation(consultation);
        entity.setTreatment(treatment);
    }
}
