package com.veterinary.mapper;

import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentStatus;
import com.veterinary.model.Owner;
import com.veterinary.model.Pet;

public final class AppointmentMapper {

    public static AppointmentResponse toResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .reason(appointment.getReason())
                .pet(PetMapper.toResponse(appointment.getPet()))
                .owner(OwnerMapper.toResponse(appointment.getOwner()))
                .status(AppointmentStatusMapper.toResponse(appointment.getAppointmentStatus()))
                .build();
    }

    public static Appointment toEntity(AppointmentRequest dto, Owner owner, Pet pet, AppointmentStatus status) {
        if (dto == null) {
            return null;
        }
        return Appointment.builder()
                .date(dto.getDate())
                .reason(dto.getReason())
                .owner(owner)
                .pet(pet)
                .appointmentStatus(status)
                .build();
    }

    public static void copyToEntity(AppointmentRequest dto, Appointment entity, Owner owner, Pet pet, AppointmentStatus status) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setDate(dto.getDate());
        entity.setReason(dto.getReason());
        entity.setOwner(owner);
        entity.setPet(pet);
        entity.setAppointmentStatus(status);
    }
}
