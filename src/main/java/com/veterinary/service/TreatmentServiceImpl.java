package com.veterinary.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.TreatmentRequest;
import com.veterinary.dto.response.TreatmentResponse;
import com.veterinary.mapper.TreatmentMapper;
import com.veterinary.model.Treatment;
import com.veterinary.repository.TreatmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository repository;

    @Override
    public List<TreatmentResponse> getTreatmentByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Treatment> treatments = repository.findByName(name, pageRequest);
        return treatments.getContent().stream()
                .map(TreatmentMapper::toResponse)
                .toList();
    }

    @Override
    public TreatmentResponse findById(Integer id) {
        Treatment treatment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found: " + id));
        return TreatmentMapper.toResponse(treatment);
    }

    @Override
    public TreatmentResponse create(TreatmentRequest request) {
        Treatment saved = repository.save(TreatmentMapper.toEntity(request));
        return TreatmentMapper.toResponse(saved);
    }

    @Override
    public TreatmentResponse update(Integer id, TreatmentRequest dto) {
        Treatment existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found: " + id));
        TreatmentMapper.copyToEntity(dto, existing);
        Treatment saved = repository.save(existing);
        return TreatmentMapper.toResponse(saved);
    }

    @Override
    public List<TreatmentResponse> getTreatmentByName(String name) {
        return repository.getTreatmentsByName(name).stream()
                .map(TreatmentMapper::toResponse)
                .toList();
    }

    @Override
    public List<TreatmentResponse> getTreatmentById(Integer id) {
        return repository.getTreatmentsById(id).stream()
                .map(TreatmentMapper::toResponse)
                .toList();
    }
}
