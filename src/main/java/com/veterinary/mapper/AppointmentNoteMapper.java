package com.veterinary.mapper;

import com.veterinary.dto.request.AppointmentNoteRequest;
import com.veterinary.dto.response.AppointmentNoteResponse;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentNote;

public final class AppointmentNoteMapper {

    public static AppointmentNoteResponse toResponse(AppointmentNote entity) {
        if (entity == null) {
            return null;
        }
        return AppointmentNoteResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .note(entity.getNote())
                .date(entity.getDate())
                .active(entity.getActive())
                .appointment(AppointmentMapper.toResponse(entity.getAppointment()))
                .build();
    }

    public static AppointmentNote toEntity(AppointmentNoteRequest dto, Appointment appointment) {
        if (dto == null) {
            return null;
        }
        return AppointmentNote.builder()
                .name(dto.getName())
                .note(dto.getNote())
                .date(dto.getDate())
                .active(true)
                .appointment(appointment)
                .build();
    }

    public static void copyToEntity(AppointmentNoteRequest dto, AppointmentNote entity, Appointment appointment) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setNote(dto.getNote());
        entity.setDate(dto.getDate());
        entity.setAppointment(appointment);
    }
}
