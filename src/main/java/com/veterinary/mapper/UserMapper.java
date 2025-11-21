package com.veterinary.mapper;

import com.veterinary.dto.request.UserRequest;
import com.veterinary.dto.response.UserResponse;
import com.veterinary.model.Role;
import com.veterinary.model.User;

public final class UserMapper {

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .role(RoleMapper.toResponse(user.getRole()))
                .build();
    }

    public static User toEntity(UserRequest dto, Role role) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .active(dto.getActive())
                .role(role)
                .build();
    }

    public static void copyToEntity(UserRequest dto, User entity, Role role) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setActive(dto.getActive());
        entity.setRole(role);
    }
}
