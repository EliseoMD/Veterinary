package com.veterinary.controller;

import java.net.URI;
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

import com.veterinary.dto.request.TreatmentRequest;
import com.veterinary.dto.response.TreatmentResponse;
import com.veterinary.service.TreatmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/treatments")
@RequiredArgsConstructor
@Tag(name = "Tratments", description = "Record of treatments received by pets")
public class TreatmentController {

    private final TreatmentService service;

    @GetMapping("/search")
    public List<TreatmentResponse> getTreatmentsByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getTreatmentByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    public TreatmentResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<TreatmentResponse> create(@Valid @RequestBody TreatmentRequest req) {
        TreatmentResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/v1/treatments/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public TreatmentResponse update(@PathVariable Integer id, @Valid @RequestBody TreatmentRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get all treatments by name")
    @GetMapping("/search/{name}")
    public List<TreatmentResponse> getTreatmentsByName(@PathVariable String name) {
        return service.getTreatmentByName(name);
    }

    @Operation(summary = "Get all treatments by Id")
    @GetMapping("/searchs/{id}")
    public List<TreatmentResponse> getTreatmentsById(@PathVariable Integer id) {
        return service.getTreatmentById(id);
    }

}
