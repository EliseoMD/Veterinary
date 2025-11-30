package com.veterinary.mapper;

import com.veterinary.dto.request.SpecieRequest;
import com.veterinary.dto.response.SpecieResponse;
import com.veterinary.model.Specie;

public final class SpecieMapper {

    public static SpecieResponse toResponse(Specie specie) {
        if (specie == null) {
            return null;
        }
        return SpecieResponse.builder()
                .id(specie.getId())
                .name(specie.getName())
                .build();
    }

    public static Specie toEntity(SpecieRequest dto) {
        if (dto == null) {
            return null;
        }
        return Specie.builder()
                .name(dto.getName())
                .build();
    }

    public static void copyToEntity(SpecieRequest dto, Specie entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
    }
}
