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

import com.veterinary.dto.request.AppointmentNoteRequest;
import com.veterinary.dto.response.AppointmentNoteResponse;
import com.veterinary.service.AppointmentNoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/appointment_notes")
@Tag(name = "Appointment Notes", description = "")
@RequiredArgsConstructor
public class AppointmentNoteController {

    private final AppointmentNoteService service;

    @GetMapping("/search")
    public List<AppointmentNoteResponse> getAppointmentNotesByDate(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getAppointmentNoteByName(name, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get online appointment by ID")
    @ApiResponse(responseCode = "200", description = "Online appointment found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentNoteResponse.class))})
    public AppointmentNoteResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new online appointment")
    public ResponseEntity<AppointmentNoteResponse> create(@Valid @RequestBody AppointmentNoteRequest req) {
        AppointmentNoteResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing online appointment")
    public AppointmentNoteResponse update(@PathVariable Integer id, @Valid @RequestBody AppointmentNoteRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get online appointments by name")
    @GetMapping("/search/{name}")
    public List<AppointmentNoteResponse> getAppointmentNotesByName(@PathVariable String name) {
        return service.getAppointmentNoteByName(name);
    }

    @Operation(summary = "Get online appointments by ID")
    @GetMapping("/search/{id}")
    public List<AppointmentNoteResponse> getAppointmentNotesById(@PathVariable Integer id) {
        return service.getAppointmentNoteById(id);
    }

}
