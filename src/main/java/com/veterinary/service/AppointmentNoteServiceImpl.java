package com.veterinary.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.veterinary.dto.request.AppointmentNoteRequest;
import com.veterinary.dto.response.AppointmentNoteResponse;
import com.veterinary.mapper.AppointmentNoteMapper;
import com.veterinary.model.Appointment;
import com.veterinary.model.AppointmentNote;
import com.veterinary.repository.AppointmentNoteRepository;
import com.veterinary.repository.AppointmentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentNoteServiceImpl implements AppointmentNoteService {

    private final AppointmentNoteRepository appointmentNoteRepository;
    private final AppointmentRepository appointmentOnlineRepository;

    @Override
    public List<AppointmentNoteResponse> getAppointmentNoteByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<AppointmentNote> notes = appointmentNoteRepository.findByName(name, pageRequest);
        return notes.getContent().stream()
                .map(AppointmentNoteMapper::toResponse)
                .toList();
    }

    @Override
    public AppointmentNoteResponse findById(Integer id) {
        AppointmentNote appointmentNote = appointmentNoteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment note not found: " + id));
        return AppointmentNoteMapper.toResponse(appointmentNote);
    }

    @Override
    public AppointmentNoteResponse create(AppointmentNoteRequest request) {
        Appointment appointment = appointmentOnlineRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found: " + request.getAppointmentId()));
        AppointmentNote saved = appointmentNoteRepository.save(AppointmentNoteMapper.toEntity(request, appointment));
        return AppointmentNoteMapper.toResponse(saved);
    }

    @Override
    public AppointmentNoteResponse update(Integer id, AppointmentNoteRequest request) {
        AppointmentNote existing = appointmentNoteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment note not found: " + id));
        Appointment appointment = appointmentOnlineRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Apointment not found: " + request.getAppointmentId()));
        AppointmentNoteMapper.copyToEntity(request, existing, appointment);
        AppointmentNote saved = appointmentNoteRepository.save(existing);
        return AppointmentNoteMapper.toResponse(saved);
    }

    @Override
    public List<AppointmentNoteResponse> getAppointmentNoteById(Integer id) {
        return appointmentNoteRepository.getAppointmentNotesById(id).stream()
                .map(AppointmentNoteMapper::toResponse)
                .toList();
    }

    @Override
    public List<AppointmentNoteResponse> getAppointmentNoteByName(String name) {
        return appointmentNoteRepository.getAppointmentNotesByName(name).stream()
                .map(AppointmentNoteMapper::toResponse)
                .toList();
    }
}
