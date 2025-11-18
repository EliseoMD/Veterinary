package com.veterinary.mapper;

import com.veterinary.dto.request.SystemUserRequest;
import com.veterinary.dto.response.SystemUserResponse;
import com.veterinary.model.SystemRole;
import com.veterinary.model.SystemUser;

public final class SystemUserMapper {

    public static SystemUserResponse toResponse(SystemUser systemUser) {
        if (systemUser == null) {
            return null;
        }
        return SystemUserResponse.builder()
                .id(systemUser.getId())
                .email(systemUser.getEmail())
                .password(systemUser.getPassword())
                .active(systemUser.getActive())
                .role(SystemRoleMapper.toResponse(systemUser.getRole()))
                .build();
    }

    public static SystemUser toEntity(SystemUserRequest dto, SystemRole role) {
        if (dto == null) {
            return null;
        }
        return SystemUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .active(dto.getActive())
                .role(role)
                .build();
    }

    public static void copyToEntity(SystemUserRequest dto, SystemUser entity, SystemRole role) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setActive(dto.getActive());
        entity.setRole(role);
    }
}
