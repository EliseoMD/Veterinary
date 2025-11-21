package com.veterinary.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.veterinary.dto.request.UserRequest;
import com.veterinary.dto.response.UserResponse;
import com.veterinary.mapper.UserMapper;
import com.veterinary.model.Role;
import com.veterinary.model.User;
import com.veterinary.repository.RoleRepository;
import com.veterinary.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUsersByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserResponse findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System user not found: " + id));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse create(UserRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("System Role not found: " + request.getRoleId()));
        User saved = userRepository.save(UserMapper.toEntity(request, role));
        return UserMapper.toResponse(saved);
    }

    @Override
    public UserResponse update(Integer id, UserRequest request) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("System user not found: " + id));
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("System Role not found: " + request.getRoleId()));
        UserMapper.copyToEntity(request, existing, role);
        User saved = userRepository.save(existing);
        return UserMapper.toResponse(saved);
    }

    @Override
    public List<UserResponse> getUserByEmail(String name) {
        return userRepository.getUsersByEmail(name).stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getUserById(Integer id) {
        return userRepository.getUsersById(id).stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
