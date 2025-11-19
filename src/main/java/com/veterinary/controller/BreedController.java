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

import com.veterinary.dto.request.BreedRequest;
import com.veterinary.dto.response.BreedResponse;
import com.veterinary.service.BreedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/breeds")
@Tag(name = "Breeds", description = "")
@RequiredArgsConstructor
public class BreedController {

    private final BreedService service;

    @GetMapping("/search")
    public List<BreedResponse> getBreedsByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getBreedByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get breed by ID")
    @ApiResponse(responseCode = "200", description = "Breed found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = BreedResponse.class))})
    public BreedResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new breed")
    public ResponseEntity<BreedResponse> create(@Valid @RequestBody BreedRequest req) {
        BreedResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing breed")
    public BreedResponse update(@PathVariable Integer id, @Valid @RequestBody BreedRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get breeds by name")
    @GetMapping("/search/{name}")
    public List<BreedResponse> getBreedsByName(@PathVariable String name) {
        return service.getBreedByName(name);
    }

    @Operation(summary = "Get breeds by ID")
    @GetMapping("/search/{id}")
    public List<BreedResponse> getBreedsById(@PathVariable Integer id) {
        return service.getBreedById(id);
    }
}
