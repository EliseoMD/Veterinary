package com.veterinary.mapper;

import com.veterinary.dto.request.VeterinarianRequest;
import com.veterinary.dto.response.VeterinarianResponse;
import com.veterinary.model.Veterinarian;

public final class VeterinarianMapper {

    public static VeterinarianResponse toResponse(Veterinarian veterinarian) {
        if (veterinarian == null)
            return null;
        return VeterinarianResponse.builder()
                .id(veterinarian.getId())
                .name(veterinarian.getName())
                .phone(veterinarian.getPhone())
                .email(veterinarian.getEmail())
                .build();
    }

    public static Veterinarian toEntity(VeterinarianRequest dto) {
        if (dto == null)
            return null;
        return Veterinarian.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

    public static void copyToEntity(VeterinarianRequest dto, Veterinarian entity) {
        if (dto == null || entity == null)
            return;
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
    }
}