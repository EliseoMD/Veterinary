package com.veterinary.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.dto.request.UserRequest;
import com.veterinary.dto.response.UserResponse;
import com.veterinary.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/system_users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "")
public class UserController {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    @Operation(summary = "Get system user by ID")
    @ApiResponse(responseCode = "200", description = "System user found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    public UserResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new system user")
    public UserResponse create(@RequestBody UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserResponse created = service.create(user);
        return created;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing system user")
    public UserResponse update(@PathVariable Integer id, @Valid @RequestBody UserRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get system users by email")
    @GetMapping("/search/name/{name}")
    public List<UserResponse> getUsersByEmail(@PathVariable String name) {
        return service.getUserByEmail(name);
    }

    @Operation(summary = "Get system users by ID")
    @GetMapping("/search/id/{id}")
    public List<UserResponse> getUsersById(@PathVariable Integer id) {
        return service.getUserById(id);
    }

}
