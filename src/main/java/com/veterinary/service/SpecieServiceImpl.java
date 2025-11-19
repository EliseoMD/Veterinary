package com.veterinary.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.SpecieRequest;
import com.veterinary.dto.response.SpecieResponse;
import com.veterinary.mapper.SpecieMapper;
import com.veterinary.model.Specie;
import com.veterinary.repository.SpecieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SpecieServiceImpl implements SpecieService {

    private final SpecieRepository repository;

    @Override
    public List<SpecieResponse> getSpecieByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Specie> species = repository.findByName(name, pageRequest);
        return species.getContent().stream()
                .map(SpecieMapper::toResponse)
                .toList();
    }

    @Override
    public SpecieResponse findById(Integer id) {
        Specie specie = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Specie not found: " + id));
        return SpecieMapper.toResponse(specie);
    }

    @Override
    public SpecieResponse create(SpecieRequest request) {
        Specie saved = repository.save(SpecieMapper.toEntity(request));
        return SpecieMapper.toResponse(saved);
    }

    @Override
    public SpecieResponse update(Integer id, SpecieRequest request) {
        Specie existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Specie not found: " + id));
        SpecieMapper.copyToEntity(request, existing);
        Specie saved = repository.save(existing);
        return SpecieMapper.toResponse(saved);
    }

    @Override
    public List<SpecieResponse> getSpecieByName(String specieName) {
        return repository.getSpeciesByName(specieName).stream()
                .map(SpecieMapper::toResponse)
                .toList();
    }

    @Override
    public List<SpecieResponse> getSpecieById(Integer specieId) {
        return repository.getSpeciesById(specieId).stream()
                .map(SpecieMapper::toResponse)
                .toList();
    }
}
