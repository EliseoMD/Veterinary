package com.veterinary.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder; // <--- 1. IMPORTANTE: Importar esto
import org.springframework.stereotype.Service;

import com.veterinary.dto.request.SystemUserRequest;
import com.veterinary.dto.response.SystemUserResponse;
import com.veterinary.mapper.SystemUserMapper;
import com.veterinary.model.SystemRole;
import com.veterinary.model.SystemUser;
import com.veterinary.repository.SystemRoleRepository;
import com.veterinary.repository.SystemUserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository userRepository;
    private final SystemRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SystemUserResponse findById(Integer id) {
        SystemUser systemUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System user not found: " + id));
        return SystemUserMapper.toResponse(systemUser);
    }

    @Override
    public SystemUserResponse create(SystemUserRequest request) {
        SystemRole role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("System Role not found: " + request.getRoleId()));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        SystemUser saved = userRepository.save(SystemUserMapper.toEntity(request, role));
        return SystemUserMapper.toResponse(saved);
    }

    @Override
    public SystemUserResponse update(Integer id, SystemUserRequest request) {
        SystemUser existing = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System user not found: " + id));
        SystemRole role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("System Role not found: " + request.getRoleId()));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        SystemUserMapper.copyToEntity(request, existing, role);
        SystemUser saved = userRepository.save(existing);
        return SystemUserMapper.toResponse(saved);
    }

    @Override
    public List<SystemUserResponse> getSystemUserByEmail(String name) {
        return userRepository.getSystemUsersByEmail(name).stream()
                .map(SystemUserMapper::toResponse)
                .toList();
    }

    @Override
    public List<SystemUserResponse> getSystemUserById(Integer id) {
        return userRepository.getSystemUsersById(id).stream()
                .map(SystemUserMapper::toResponse)
                .toList();
    }

    @Override
    public List<SystemUserResponse> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
