package com.veterinary.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;
import com.veterinary.mapper.AppointmentMapper;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentStatus;
import com.veterinary.repository.AppointmentRepository;
import com.veterinary.repository.AppointmentStatusRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentStatusRepository statusRepository;

    @Override
    public List<AppointmentResponse> getAppointmentByDate(LocalDateTime date, int page, int pageSize) {
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
        AppointmentStatus status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found: " + request.getStatusId()));
        Appointment entity = AppointmentMapper.toEntity(request, status);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setActive(true);
        Appointment saved = appointmentRepository.save(entity);
        return AppointmentMapper.toResponse(saved);
    }

    @Override
    public AppointmentResponse update(Integer id, AppointmentRequest request) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment online not found: " + id));
        AppointmentStatus status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found: " + request.getStatusId()));
        AppointmentMapper.copyToEntity(request, existing, status);
        Appointment saved = appointmentRepository.save(existing);
        return AppointmentMapper.toResponse(saved);
    }

    @Override
    public List<AppointmentResponse> getAppointmentByDate(LocalDateTime date) {
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
