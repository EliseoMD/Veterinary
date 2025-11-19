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
import com.veterinary.dto.request.PetRequest;
import com.veterinary.dto.response.PetResponse;
import com.veterinary.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pets")
@Tag(name = "Pets", description = "")
@RequiredArgsConstructor
public class PetController {

    private final PetService service;

    @GetMapping("/search")
    public List<PetResponse> getPetsByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getPetByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get pet by ID")
    @ApiResponse(responseCode = "200", description = "Pet found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = PetResponse.class))})
    public PetResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new pet")
    public ResponseEntity<PetResponse> create(@Valid @RequestBody PetRequest req) {
        PetResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing pet")
    public PetResponse update(@PathVariable Integer id, @Valid @RequestBody PetRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get pets by name")
    @GetMapping("/search/{name}")
    public List<PetResponse> getPetByName(@PathVariable String name) {
        return service.getPetByName(name);
    }

    @Operation(summary = "Get pets by ID")
    @GetMapping("/search/{id}")
    public List<PetResponse> getPetById(@PathVariable Integer id) {
        return service.getPetById(id);
    }
}
