package com.veterinary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.veterinary.dto.request.SystemRoleRequest;
import com.veterinary.dto.response.SystemRoleResponse;
import com.veterinary.mapper.SystemRoleMapper;
import com.veterinary.model.SystemRole;
import com.veterinary.repository.SystemRoleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SystemRoleServiceImpl implements SystemRoleService {

    private final SystemRoleRepository repository;

    @Override
    public SystemRoleResponse findById(Integer id) {
        SystemRole systemRole = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System role not found: " + id));
        return SystemRoleMapper.toResponse(systemRole);
    }

    @Override
    public SystemRoleResponse create(SystemRoleRequest request) {
        SystemRole saved = repository.save(SystemRoleMapper.toEntity(request));
        return SystemRoleMapper.toResponse(saved);
    }

    @Override
    public SystemRoleResponse update(Integer id, SystemRoleRequest request) {
        SystemRole existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System role not found: " + id));
        SystemRoleMapper.copyToEntity(request, existing);
        SystemRole saved = repository.save(existing);
        return SystemRoleMapper.toResponse(saved);
    }

    @Override
    public List<SystemRoleResponse> getRoleByName(String role_name) {
        return repository.getRolesByName(role_name).stream()
                .map(SystemRoleMapper::toResponse)
                .toList();
    }

    @Override
    public List<SystemRoleResponse> getRoleById(Integer role_id) {
        return repository.getRolesById(role_id).stream()
                .map(SystemRoleMapper::toResponse)
                .toList();
    }

    @Override
    public List<SystemRoleResponse> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
