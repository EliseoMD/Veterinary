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

import com.veterinary.dto.request.AppointmentStatusRequest;
import com.veterinary.dto.response.AppointmentStatusResponse;
import com.veterinary.service.AppointmentStatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/appointment_status")
@Tag(name = "Appointment Status", description = "")
@RequiredArgsConstructor
public class AppointmentStatusController {

    private final AppointmentStatusService service;

    @GetMapping("/search")
    public List<AppointmentStatusResponse> getAppointmentStatussByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getAppointmentStatusByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment status by ID")
    @ApiResponse(responseCode = "200", description = "Appointment status found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentStatusResponse.class))})
    public AppointmentStatusResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new appointment status")
    public ResponseEntity<AppointmentStatusResponse> create(@Valid @RequestBody AppointmentStatusRequest req) {
        AppointmentStatusResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing appointment status")
    public AppointmentStatusResponse update(@PathVariable Integer id, @Valid @RequestBody AppointmentStatusRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get appointment status by name")
    @GetMapping("/search/{name}")
    public List<AppointmentStatusResponse> getStatusesByName(@PathVariable String name) {
        return service.getAppointmentStatusByName(name);
    }

    @Operation(summary = "Get appointment status by ID")
    @GetMapping("/search/{id}")
    public List<AppointmentStatusResponse> getStatusesById(@PathVariable Integer id) {
        return service.getAppointmentStatusById(id);
    }
}
