package com.veterinary.mapper;

import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentStatus;

public final class AppointmentMapper {

    public static AppointmentResponse toResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .reason(appointment.getReason())
                .active(appointment.getActive())
                .pet(appointment.getPetId())
                .owner(appointment.getOwnerId())
                .status(AppointmentStatusMapper.toResponse(appointment.getAppointmentStatus()))
                .build();
    }

    public static Appointment toEntity(AppointmentRequest dto, AppointmentStatus status) {
        if (dto == null) {
            return null;
        }
        return Appointment.builder()
                .date(dto.getDate())
                .reason(dto.getReason())
                .petId(dto.getPetId())
                .ownerId(dto.getOwnerId())
                .appointmentStatus(status)
                .build();
    }

    public static void copyToEntity(AppointmentRequest dto, Appointment entity, AppointmentStatus status) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setDate(dto.getDate());
        entity.setReason(dto.getReason());
        entity.setPetId(dto.getPetId());
        entity.setOwnerId(dto.getOwnerId());
        entity.setAppointmentStatus(status);
    }
}
