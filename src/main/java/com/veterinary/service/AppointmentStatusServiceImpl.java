package com.veterinary.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.dto.request.AppointmentStatusRequest;
import com.veterinary.dto.response.AppointmentStatusResponse;
import com.veterinary.mapper.AppointmentStatusMapper;
import com.veterinary.model.AppointmentStatus;
import com.veterinary.repository.AppointmentStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AppointmentStatusServiceImpl implements AppointmentStatusService {

    private final AppointmentStatusRepository repository;

    @Override
    public List<AppointmentStatusResponse> getAppointmentStatusByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<AppointmentStatus> status = repository.findByName(name, pageRequest);
        return status.getContent().stream()
                .map(AppointmentStatusMapper::toResponse)
                .toList();
    }

    @Override
    public AppointmentStatusResponse findById(Integer statusId) {
        AppointmentStatus appointmentStatus = repository.findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment status not found: " + statusId));
        return AppointmentStatusMapper.toResponse(appointmentStatus);
    }

    @Override
    public AppointmentStatusResponse create(AppointmentStatusRequest request) {
        AppointmentStatus saved = repository.save(AppointmentStatusMapper.toEntity(request));
        return AppointmentStatusMapper.toResponse(saved);
    }

    @Override
    public AppointmentStatusResponse update(Integer id, AppointmentStatusRequest dto) {
        AppointmentStatus existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment status not found: " + id));
        AppointmentStatusMapper.copyToEntity(dto, existing);
        AppointmentStatus saved = repository.save(existing);
        return AppointmentStatusMapper.toResponse(saved);
    }

    @Override
    public List<AppointmentStatusResponse> getAppointmentStatusByName(String name) {
        return repository.getAppointmentStatusByName(name).stream()
                .map(AppointmentStatusMapper::toResponse)
                .toList();
    }

    @Override
    public List<AppointmentStatusResponse> getAppointmentStatusById(Integer id) {
        return repository.getAppointmentStatusById(id).stream()
                .map(AppointmentStatusMapper::toResponse)
                .toList();
    }

}
