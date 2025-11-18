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

import com.veterinary.dto.request.SystemRoleRequest;
import com.veterinary.dto.response.SystemRoleResponse;
import com.veterinary.service.SystemRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/system_roles")
@RequiredArgsConstructor
@Tag(name = "System Roles", description = "")
public class SystemRoleController {

    private final SystemRoleService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get system role by ID")
    @ApiResponse(responseCode = "200", description = "System role found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SystemRoleResponse.class)) })
    public SystemRoleResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new system role")
    public ResponseEntity<SystemRoleResponse> create(@Valid @RequestBody SystemRoleRequest req) {
        SystemRoleResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing system role")
    public SystemRoleResponse update(@PathVariable Integer id, @Valid @RequestBody SystemRoleRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get system roles by name")
    @GetMapping("/search/name/{name}")
    public List<SystemRoleResponse> getRolesByName(@PathVariable String name) {
        return service.getRoleByName(name);
    }

    @Operation(summary = "Get system roles by ID")
    @GetMapping("/search/id/{id}")
    public List<SystemRoleResponse> getRolesById(@PathVariable Integer id) {
        return service.getRoleById(id);
    }
}