package com.veterinary.controller;

import java.sql.Timestamp;
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

import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;
import com.veterinary.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/appointments")
@Tag(name = "Appointments", description = "")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping("/search")
    public List<AppointmentResponse> getAppointmentsByDate(
            @RequestParam Timestamp date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getAppointmentByDate(date, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID")
    @ApiResponse(responseCode = "200", description = "Online appointment found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))})
    public AppointmentResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new appointment")
    public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody AppointmentRequest req) {
        AppointmentResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing appointment")
    public AppointmentResponse update(@PathVariable Integer id, @Valid @RequestBody AppointmentRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get appointments by date")
    @GetMapping("/search/{date}")
    public List<AppointmentResponse> getAppointsByDate(@PathVariable Timestamp date) {
        return service.getAppointmentByDate(date);
    }

    @Operation(summary = "Get appointments by ID")
    @GetMapping("/search/{id}")
    public List<AppointmentResponse> getAppointmentsById(@PathVariable Integer id) {
        return service.getAppointmentById(id);
    }
}
