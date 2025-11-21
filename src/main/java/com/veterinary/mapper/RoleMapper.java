package com.veterinary.mapper;

import com.veterinary.dto.request.RoleRequest;
import com.veterinary.dto.response.RoleResponse;
import com.veterinary.model.Role;

public final class RoleMapper {

    public static RoleResponse toResponse(Role role) {
        if (role == null) {
            return null;
        }
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleRequest dto) {
        if (dto == null) {
            return null;
        }
        return Role.builder()
                .name(dto.getName())
                .build();
    }

    public static void copyToEntity(RoleRequest dto, Role entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
    }
}
