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

import com.veterinary.dto.request.AppointmentCancelRequest;
import com.veterinary.dto.response.AppointmentCancelResponse;
import com.veterinary.service.AppointmentCancelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/appointment_cancels")
@Tag(name = "Appointment Cancels", description = "Management of appointment cancellations")
@RequiredArgsConstructor
public class AppointmentCancelController {

    private final AppointmentCancelService service;

    @GetMapping("/{id}")
    @Operation(summary = "Get cancellation by ID")
    @ApiResponse(responseCode = "200", description = "Cancellation found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentCancelResponse.class))})
    public AppointmentCancelResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new cancellation")
    public ResponseEntity<AppointmentCancelResponse> create(@Valid @RequestBody AppointmentCancelRequest req) {
        AppointmentCancelResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing cancellation")
    public AppointmentCancelResponse update(@PathVariable Integer id, @Valid @RequestBody AppointmentCancelRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get cancellations by ID")
    @GetMapping("/search/{id}")
    public List<AppointmentCancelResponse> getCancelsById(@PathVariable Integer id) {
        return service.getCancelById(id);
    }
}
