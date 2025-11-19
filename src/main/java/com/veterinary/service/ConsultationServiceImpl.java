package com.veterinary.service;

import com.veterinary.repository.ConsultationRepository;
import com.veterinary.repository.VeterinarianRepository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.ConsultationRequest;
import com.veterinary.dto.response.ConsultationResponse;
import com.veterinary.mapper.ConsultationMapper;
import com.veterinary.model.Consultation;
import com.veterinary.model.Veterinarian;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository repository;
    private final VeterinarianRepository veterinaryRepository;

    @Override
    public List<ConsultationResponse> getConsultationByDate(Timestamp date, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Consultation> consultations = repository.findByDate(date, pageRequest);
        return consultations.getContent().stream()
                .map(ConsultationMapper::toResponse)
                .toList();
    }

    @Override
    public ConsultationResponse findById(Integer id) {
        Consultation consultation = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
        return ConsultationMapper.toResponse(consultation);
    }

    @Override
    public ConsultationResponse create(ConsultationRequest request) {
        Veterinarian veterinarian = veterinaryRepository.findById(request.getVeterinarianId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinarian not found: " + request.getVeterinarianId()));
        Consultation saved = repository.save(ConsultationMapper.toEntity(request, veterinarian));
        return ConsultationMapper.toResponse(saved);
    }

    @Override
    public ConsultationResponse update(Integer id, ConsultationRequest request) {
        Consultation existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
        Veterinarian veterinarian = veterinaryRepository.findById(request.getVeterinarianId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinarian not found: "));
        ConsultationMapper.copyToEntity(request, existing, veterinarian);
        Consultation saved = repository.save(existing);
        return ConsultationMapper.toResponse(saved);
    }

    @Override
    public List<ConsultationResponse> getConsultationByDate(Timestamp consultation_date) {
        return repository.getConsultationsByDate(consultation_date).stream()
                .map(ConsultationMapper::toResponse)
                .toList();
    }

    @Override
    public List<ConsultationResponse> getConsultationById(Integer consultation_id) {
        return repository.getConsultationsById(consultation_id).stream()
                .map(ConsultationMapper::toResponse)
                .toList();
    }

}
