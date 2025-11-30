package com.veterinary.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.OwnerRequest;
import com.veterinary.dto.response.OwnerResponse;
import com.veterinary.mapper.OwnerMapper;
import com.veterinary.model.Owner;
import com.veterinary.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository repository;

    @Override
    public List<OwnerResponse> getOwnerByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Owner> owners = repository.findByName(name, pageRequest);
        return owners.getContent().stream()
                .map(OwnerMapper::toResponse)
                .toList();
    }

    @Override
    public OwnerResponse findById(Integer id) {
        Owner owner = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found: " + id));
        return OwnerMapper.toResponse(owner);
    }

    @Override
    public OwnerResponse create(OwnerRequest request) {
        Owner saved = repository.save(OwnerMapper.toEntity(request));
        return OwnerMapper.toResponse(saved);
    }

    @Override
    public OwnerResponse update(Integer id, OwnerRequest request) {
        Owner existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found: " + id));
        OwnerMapper.copyToEntity(request, existing);
        Owner saved = repository.save(existing);
        return OwnerMapper.toResponse(saved);
    }

    @Override
    public List<OwnerResponse> getOwnerByName(String ownerName) {
        return repository.getOwnersByName(ownerName).stream()
                .map(OwnerMapper::toResponse)
                .toList();
    }

    @Override
    public List<OwnerResponse> getOwnerById(Integer ownerId) {
        return repository.getOwnersById(ownerId).stream()
                .map(OwnerMapper::toResponse)
                .toList();
    }

}
