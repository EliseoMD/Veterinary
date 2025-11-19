package com.veterinary.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;
import com.veterinary.mapper.AppointmentMapper;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentStatus;
import com.veterinary.model.Owner;
import com.veterinary.model.Pet;
import com.veterinary.repository.AppointmentRepository;
import com.veterinary.repository.AppointmentStatusRepository;
import com.veterinary.repository.OwnerRepository;
import com.veterinary.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final AppointmentStatusRepository statusRepository;

    @Override
    public List<AppointmentResponse> getAppointmentByDate(Timestamp date, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Appointment> appointments = appointmentRepository.findByDate(date, pageRequest);
        return appointments.getContent().stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }

    @Override
    public AppointmentResponse findById(Integer appointmentId) {
        Appointment appointmentOnline = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment online not found: " + appointmentId));
        return AppointmentMapper.toResponse(appointmentOnline);
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found."));
        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found."));
        AppointmentStatus status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found."));
        Appointment saved = appointmentRepository.save(AppointmentMapper.toEntity(request, owner, pet, status));
        return AppointmentMapper.toResponse(saved);
    }

    @Override
    public AppointmentResponse update(Integer id, AppointmentRequest request) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment online not found: " + id));
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found."));
        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found."));
        AppointmentStatus status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found."));
        AppointmentMapper.copyToEntity(request, existing, owner, pet, status);
        Appointment saved = appointmentRepository.save(existing);
        return AppointmentMapper.toResponse(saved);
    }

    @Override
    public List<AppointmentResponse> getAppointmentByDate(Timestamp date) {
        return appointmentRepository.getAppointmentByDate(date).stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }

    @Override
    public List<AppointmentResponse> getAppointmentById(Integer id) {
        return appointmentRepository.getAppointmentById(id).stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }
}
