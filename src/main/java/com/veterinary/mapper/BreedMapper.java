package com.veterinary.mapper;

import com.veterinary.dto.request.BreedRequest;
import com.veterinary.dto.response.BreedResponse;
import com.veterinary.model.Breed;
import com.veterinary.model.Specie;

public final class BreedMapper {

    public static BreedResponse toResponse(Breed breed) {
        if (breed == null) {
            return null;
        }
        return BreedResponse.builder()
                .id(breed.getId())
                .name(breed.getName())
                .specie(SpecieMapper.toResponse(breed.getSpecie()))
                .build();
    }

    public static Breed toEntity(BreedRequest dto, Specie specie) {
        if (dto == null) {
            return null;
        }
        return Breed.builder()
                .name(dto.getName())
                .specie(specie)
                .build();
    }

    public static void copyToEntity(BreedRequest dto, Breed entity, Specie specie) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setSpecie(specie);
    }
}
