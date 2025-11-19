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
import com.veterinary.dto.request.SpecieRequest;
import com.veterinary.dto.response.ConsultationResponse;
import com.veterinary.dto.response.SpecieResponse;
import com.veterinary.service.SpecieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/species")
@Tag(name = "Species", description = "")
@RequiredArgsConstructor
public class SpecieController {

    private final SpecieService service;

    @GetMapping("/search")
    public List<SpecieResponse> getSpeciesByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getSpecieByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specie by ID")
    @ApiResponse(responseCode = "200", description = "Specie found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = SpecieResponse.class))})
    public SpecieResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new specie")
    public ResponseEntity<SpecieResponse> create(@Valid @RequestBody SpecieRequest req) {
        SpecieResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing specie")
    public SpecieResponse update(@PathVariable Integer id, @Valid @RequestBody SpecieRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get species by name")
    @GetMapping("/search/{name}")
    public List<SpecieResponse> getSpeciesByName(@PathVariable String name) {
        return service.getSpecieByName(name);
    }

    @Operation(summary = "Get species by ID")
    @GetMapping("/search/{id}")
    public List<SpecieResponse> getSpeciesById(@PathVariable Integer id) {
        return service.getSpecieById(id);
    }
}
