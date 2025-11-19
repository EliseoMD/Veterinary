package com.veterinary.mapper;

import com.veterinary.dto.request.AppointmentNoteRequest;
import com.veterinary.dto.response.AppointmentNoteResponse;
import com.veterinary.model.AppointmentNote;
import com.veterinary.model.Appointment;

public final class AppointmentNoteMapper {

    public static AppointmentNoteResponse toResponse(AppointmentNote appointmentNote) {
        if (appointmentNote == null) {
            return null;
        }
        return AppointmentNoteResponse.builder()
                .id(appointmentNote.getId())
                .name(appointmentNote.getName())
                .note(appointmentNote.getNote())
                .date(appointmentNote.getDate())
                .appointment(AppointmentMapper.toResponse(appointmentNote.getAppointment()))
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
