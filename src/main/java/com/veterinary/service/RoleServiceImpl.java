package com.veterinary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.veterinary.dto.request.RoleRequest;
import com.veterinary.dto.response.RoleResponse;
import com.veterinary.mapper.RoleMapper;
import com.veterinary.model.Role;
import com.veterinary.repository.RoleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public RoleResponse findById(Integer id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System role not found: " + id));
        return RoleMapper.toResponse(role);
    }

    @Override
    public RoleResponse create(RoleRequest request) {
        Role saved = repository.save(RoleMapper.toEntity(request));
        return RoleMapper.toResponse(saved);
    }

    @Override
    public RoleResponse update(Integer id, RoleRequest request) {
        Role existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System role not found: " + id));
        RoleMapper.copyToEntity(request, existing);
        Role saved = repository.save(existing);
        return RoleMapper.toResponse(saved);
    }

    @Override
    public List<RoleResponse> getRoleByName(String role_name) {
        return repository.getRolesByName(role_name).stream()
                .map(RoleMapper::toResponse)
                .toList();
    }

    @Override
    public List<RoleResponse> getRoleById(Integer role_id) {
        return repository.getRolesById(role_id).stream()
                .map(RoleMapper::toResponse)
                .toList();
    }

    @Override
    public List<RoleResponse> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
