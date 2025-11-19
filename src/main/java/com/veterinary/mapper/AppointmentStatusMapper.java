package com.veterinary.mapper;

import com.veterinary.dto.request.AppointmentStatusRequest;
import com.veterinary.dto.response.AppointmentStatusResponse;
import com.veterinary.model.AppointmentStatus;

public final class AppointmentStatusMapper {

    public static AppointmentStatusResponse toResponse(AppointmentStatus appointmentStatus) {
        if (appointmentStatus == null) {
            return null;
        }
        return AppointmentStatusResponse.builder()
                .id(appointmentStatus.getId())
                .name(appointmentStatus.getName())
                .build();
    }

    public static AppointmentStatus toEntity(AppointmentStatusRequest dto) {
        if (dto == null) {
            return null;
        }
        return AppointmentStatus.builder()
                .name(dto.getName())
                .build();
    }

    public static void copyToEntity(AppointmentStatusRequest dto, AppointmentStatus entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
    }
}
