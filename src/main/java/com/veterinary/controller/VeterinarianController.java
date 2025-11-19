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
import com.veterinary.dto.request.VeterinarianRequest;
import com.veterinary.dto.response.VeterinarianResponse;
import com.veterinary.service.VeterinarianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/veterinarians")
@Tag(name = "Veterinarians", description = "")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianService service;

    @GetMapping("/search")
    public List<VeterinarianResponse> getVeterinariansByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getVeterinarianByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get veterinarian by ID")
    @ApiResponse(responseCode = "200", description = "Veterinarian found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = VeterinarianResponse.class))})
    public VeterinarianResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new veterinarian")
    public ResponseEntity<VeterinarianResponse> create(@Valid @RequestBody VeterinarianRequest req) {
        VeterinarianResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing veterinarian")
    public VeterinarianResponse update(@PathVariable Integer id, @Valid @RequestBody VeterinarianRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get veterinarians by name")
    @GetMapping("/search/{name}")
    public List<VeterinarianResponse> getVeterinarianByName(@PathVariable String veterinarianName) {
        return service.getVeterinarianByName(veterinarianName);
    }

    @Operation(summary = "Get veterinarians by ID")
    @GetMapping("/search/{id}")
    public List<VeterinarianResponse> getVeterinarianById(@PathVariable Integer veterinarianId) {
        return service.getVeterinarianById(veterinarianId);
    }
}
