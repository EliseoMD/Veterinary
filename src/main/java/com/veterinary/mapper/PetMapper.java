package com.veterinary.mapper;

import com.veterinary.dto.request.PetRequest;
import com.veterinary.dto.response.PetResponse;
import com.veterinary.model.Breed;
import com.veterinary.model.Owner;
import com.veterinary.model.Pet;
import com.veterinary.model.Specie;

public final class PetMapper {

    public static PetResponse toResponse(Pet pet) {
        if (pet == null) {
            return null;
        }
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .age(pet.getAge())
                .owner(OwnerMapper.toResponse(pet.getOwner()))
                .specie(SpecieMapper.toResponse(pet.getSpecie()))
                .breed(BreedMapper.toResponse(pet.getBreed()))
                .build();
    }

    public static Pet toEntity(PetRequest dto, Owner owner, Specie specie, Breed breed) {
        if (dto == null) {
            return null;
        }
        return Pet.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .age(dto.getAge())
                .owner(owner)
                .specie(specie)
                .breed(breed)
                .build();
    }

    public static void copyToEntity(PetRequest dto, Pet entity, Owner owner, Specie specie, Breed breed) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setAge(dto.getAge());
        entity.setOwner(owner);
        entity.setSpecie(specie);
        entity.setBreed(breed);
    }
}
