package com.veterinary.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.VeterinarianRequest;
import com.veterinary.dto.response.VeterinarianResponse;
import com.veterinary.mapper.VeterinarianMapper;
import com.veterinary.model.Veterinarian;
import com.veterinary.repository.VeterinarianRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class VeterinarianServiceImpl implements VeterinarianService {

    private final VeterinarianRepository repository;

    @Override
    public List<VeterinarianResponse> getVeterinarianByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Veterinarian> veterinarians = repository.findByName(name, pageRequest);
        return veterinarians.getContent().stream()
                .map(VeterinarianMapper::toResponse)
                .toList();
    }

    @Override
    public VeterinarianResponse findById(Integer id) {
        Veterinarian veterinarian = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veterinarian not found: " + id));
        return VeterinarianMapper.toResponse(veterinarian);
    }

    @Override
    public VeterinarianResponse create(VeterinarianRequest request) {
        Veterinarian saved = repository.save(VeterinarianMapper.toEntity(request));
        return VeterinarianMapper.toResponse(saved);
    }

    @Override
    public VeterinarianResponse update(Integer id, VeterinarianRequest request) {
        Veterinarian existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veterinarian not found: " + id));
        VeterinarianMapper.copyToEntity(request, existing);
        Veterinarian saved = repository.save(existing);
        return VeterinarianMapper.toResponse(saved);
    }

    @Override
    public List<VeterinarianResponse> getVeterinarianByName(String name) {
        return repository.getVeterinariansByName(name).stream()
                .map(VeterinarianMapper::toResponse)
                .toList();
    }

    @Override
    public List<VeterinarianResponse> getVeterinarianById(Integer id) {
        return repository.getVeterinariansById(id).stream()
                .map(VeterinarianMapper::toResponse)
                .toList();
    }

}
