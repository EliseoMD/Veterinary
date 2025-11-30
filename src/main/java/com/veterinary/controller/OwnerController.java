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
import com.veterinary.dto.request.OwnerRequest;
import com.veterinary.dto.response.OwnerResponse;
import com.veterinary.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/owners")
@Tag(name = "Owners", description = "")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService service;

    @GetMapping("/search")
    public List<OwnerResponse> getOwnersByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getOwnerByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get owner by ID")
    @ApiResponse(responseCode = "200", description = "Owner found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = OwnerResponse.class))})
    public OwnerResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new owner")
    public ResponseEntity<OwnerResponse> create(@Valid @RequestBody OwnerRequest req) {
        OwnerResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing owner")
    public OwnerResponse update(@PathVariable Integer id, @Valid @RequestBody OwnerRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get owners by name")
    @GetMapping("/search/{name}")
    public List<OwnerResponse> getOwnersByName(@PathVariable String name) {
        return service.getOwnerByName(name);
    }

    @Operation(summary = "Get owners by ID")
    @GetMapping("/search/{id}")
    public List<OwnerResponse> getOwnersById(@PathVariable Integer id) {
        return service.getOwnerById(id);
    }
}
