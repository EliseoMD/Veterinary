package com.veterinary.mapper;

import com.veterinary.dto.request.OwnerRequest;
import com.veterinary.dto.response.OwnerResponse;
import com.veterinary.model.Owner;

public final class OwnerMapper {

    public static OwnerResponse toResponse(Owner owner) {
        if (owner == null) {
            return null;
        }
        return OwnerResponse.builder()
                .id(owner.getId())
                .name(owner.getName())
                .phone(owner.getPhone())
                .address(owner.getAddress())
                .active(owner.getActive())
                .build();
    }

    public static Owner toEntity(OwnerRequest dto) {
        if (dto == null) {
            return null;
        }
        return Owner.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .active(dto.getActive())
                .build();
    }

    public static void copyToEntity(OwnerRequest dto, Owner entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setActive(dto.getActive());
    }
}
