package com.veterinary.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.veterinary.dto.request.RoleRequest;
import com.veterinary.dto.response.RoleResponse;
import com.veterinary.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "Roles", description = "")
public class RoleController {

    private final RoleService service;

    @GetMapping("/search")
    public List<RoleResponse> getRolesByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getRoleByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get system role by ID")
    @ApiResponse(responseCode = "200", description = "System role found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))})

    public RoleResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new system role")
    public ResponseEntity<RoleResponse> create(@Valid @RequestBody RoleRequest req) {
        RoleResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing system role")
    public RoleResponse update(@PathVariable Integer id, @Valid @RequestBody RoleRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get system roles by name")
    @GetMapping("/search/{name}")
    public List<RoleResponse> getRolesByName(@PathVariable String name) {
        return service.getRoleByName(name);
    }

    @Operation(summary = "Get system roles by ID")
    @GetMapping("/search/{id}")
    public List<RoleResponse> getRolesById(@PathVariable Integer id) {
        return service.getRoleById(id);
    }
}
