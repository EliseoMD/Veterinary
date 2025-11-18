package com.veterinary.mapper;

import com.veterinary.dto.request.SystemRoleRequest;
import com.veterinary.dto.response.SystemRoleResponse;
import com.veterinary.model.SystemRole;

public final class SystemRoleMapper {

    public static SystemRoleResponse toResponse(SystemRole systemRole) {
        if (systemRole == null) {
            return null;
        }
        return SystemRoleResponse.builder()
                .id(systemRole.getId())
                .name(systemRole.getName())
                .active(systemRole.getActive())
                .build();
    }

    public static SystemRole toEntity(SystemRoleRequest dto) {
        if (dto == null) {
            return null;
        }
        return SystemRole.builder()
                .name(dto.getName())
                .active(dto.getActive())
                .build();
    }

    public static void copyToEntity(SystemRoleRequest dto, SystemRole entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setActive(dto.getActive());
    }
}
