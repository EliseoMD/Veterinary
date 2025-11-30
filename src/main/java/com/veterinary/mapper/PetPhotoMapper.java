package com.veterinary.mapper;

import com.veterinary.dto.request.PetPhotoRequest;
import com.veterinary.dto.response.PetPhotoResponse;
import com.veterinary.model.Pet;
import com.veterinary.model.PetPhoto;

public final class PetPhotoMapper {

    public static PetPhotoResponse toResponse(PetPhoto entity) {
        if (entity == null) {
            return null;
        }
        return PetPhotoResponse.builder()
                .id(entity.getId())
                .petId(entity.getPet().getId())
                .url(entity.getUrl())
                .uploadDate(entity.getUploadDate())
                .build();
    }

    public static PetPhoto toEntity(PetPhotoRequest dto, Pet pet) {
        if (dto == null) {
            return null;
        }
        return PetPhoto.builder()
                .url(dto.getUrl())
                .pet(pet)
                .build();
    }

    public static void copyToEntity(PetPhotoRequest dto, PetPhoto entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setUrl(dto.getUrl());
        // Nota: Generalmente no cambiamos la mascota de una foto, pero si fuera necesario se agregaría aquí.
    }
}
