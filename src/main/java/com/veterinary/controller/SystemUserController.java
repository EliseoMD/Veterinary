package com.veterinary.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinary.dto.request.SystemUserRequest;
import com.veterinary.dto.response.SystemUserResponse;
import com.veterinary.service.SystemUserService;

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
@Tag(name = "System Users", description = "")
public class SystemUserController {

    private final SystemUserService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get system user by ID")
    @ApiResponse(responseCode = "200", description = "System user found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = SystemUserResponse.class))})
    public SystemUserResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new system user")
    public ResponseEntity<SystemUserResponse> create(@Valid @RequestBody SystemUserRequest req) {
        SystemUserResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing system user")
    public SystemUserResponse update(@PathVariable Integer id, @Valid @RequestBody SystemUserRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get system users by name")
    @GetMapping("/search/name/{name}")
    public List<SystemUserResponse> getSystemUsersByEmail(@PathVariable String email) {
        return service.getSystemUserByEmail(email);
    }

    @Operation(summary = "Get system users by ID")
    @GetMapping("/search/id/{id}")
    public List<SystemUserResponse> getSystemUsersById(@PathVariable Integer id) {
        return service.getSystemUserById(id);
    }
}
