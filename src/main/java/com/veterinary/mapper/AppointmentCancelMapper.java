package com.veterinary.mapper;

import com.veterinary.dto.request.AppointmentCancelRequest;
import com.veterinary.dto.response.AppointmentCancelResponse;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentCancel;

public final class AppointmentCancelMapper {

    public static AppointmentCancelResponse toResponse(AppointmentCancel entity) {
        if (entity == null) {
            return null;
        }
        return AppointmentCancelResponse.builder()
                .id(entity.getId())
                .appointmentId(entity.getAppointment().getId())
                .cancelDate(entity.getCancelDate())
                .reason(entity.getReason())
                .active(entity.getActive())
                .build();
    }

    public static AppointmentCancel toEntity(AppointmentCancelRequest dto, Appointment appointment) {
        if (dto == null) {
            return null;
        }
        return AppointmentCancel.builder()
                .appointment(appointment)
                .reason(dto.getReason())
                .active(dto.getActive())
                .build();
    }

    public static void copyToEntity(AppointmentCancelRequest dto, AppointmentCancel entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setReason(dto.getReason());
    }
}
