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
import com.veterinary.dto.request.PetPhotoRequest;
import com.veterinary.dto.response.PetPhotoResponse;
import com.veterinary.service.PetPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pet_photos")
@Tag(name = "Pet Photos", description = "Management of pet photos")
@RequiredArgsConstructor
public class PetPhotoController {

    private final PetPhotoService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get photo by ID")
    @ApiResponse(responseCode = "200", description = "Photo found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = PetPhotoResponse.class))})
    public PetPhotoResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new photo")
    public ResponseEntity<PetPhotoResponse> create(@Valid @RequestBody PetPhotoRequest req) {
        PetPhotoResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update photo URL")
    public PetPhotoResponse update(@PathVariable Integer id, @Valid @RequestBody PetPhotoRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get photos by Pet ID")
    @GetMapping("/search/pet/{petId}")
    public List<PetPhotoResponse> getPhotosByPetId(@PathVariable Integer petId) {
        return service.getPhotosByPetId(petId);
    }
}
