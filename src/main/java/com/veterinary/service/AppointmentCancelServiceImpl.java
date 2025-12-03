package com.veterinary.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.veterinary.dto.request.AppointmentCancelRequest;
import com.veterinary.dto.response.AppointmentCancelResponse;
import com.veterinary.mapper.AppointmentCancelMapper;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentCancel;
import com.veterinary.repository.AppointmentCancelRepository;
import com.veterinary.repository.AppointmentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentCancelServiceImpl implements AppointmentCancelService {

    private final AppointmentCancelRepository repository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentCancelResponse findById(Integer id) {
        AppointmentCancel entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cancellation not found: " + id));
        return AppointmentCancelMapper.toResponse(entity);
    }

    @Override
    public AppointmentCancelResponse create(AppointmentCancelRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + request.getAppointmentId()));

        AppointmentCancel entity = AppointmentCancelMapper.toEntity(request, appointment);
        entity.setCancelDate(LocalDateTime.now());

        AppointmentCancel saved = repository.save(entity);
        return AppointmentCancelMapper.toResponse(saved);
    }

    @Override
    public AppointmentCancelResponse update(Integer id, AppointmentCancelRequest request) {
        AppointmentCancel existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cancellation not found: " + id));

        AppointmentCancelMapper.copyToEntity(request, existing);
        AppointmentCancel saved = repository.save(existing);
        return AppointmentCancelMapper.toResponse(saved);
    }

    @Override
    public List<AppointmentCancelResponse> getCancelById(Integer id) {
        return repository.getCancelById(id).stream()
                .map(AppointmentCancelMapper::toResponse)
                .toList();
    }
}
