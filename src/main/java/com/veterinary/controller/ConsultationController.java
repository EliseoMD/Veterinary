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
import com.veterinary.dto.request.ConsultationRequest;
import com.veterinary.dto.response.ConsultationResponse;
import com.veterinary.service.ConsultationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/consultations")
@Tag(name = "Consultations", description = "")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService service;

    @GetMapping("/search")
    public List<ConsultationResponse> getConsultationsByDate(
            @RequestParam Timestamp date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getConsultationByDate(date, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get consultation by ID")
    @ApiResponse(responseCode = "200", description = "Consultation found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationResponse.class))})
    public ConsultationResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new consultation")
    public ResponseEntity<ConsultationResponse> create(@Valid @RequestBody ConsultationRequest req) {
        ConsultationResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing consultation")
    public ConsultationResponse update(@PathVariable Integer id, @Valid @RequestBody ConsultationRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get consultations by date")
    @GetMapping("/search/{date}")
    public List<ConsultationResponse> getConsultationsByDate(@PathVariable Timestamp date) {
        return service.getConsultationByDate(date);
    }

    @Operation(summary = "Get consultations by ID")
    @GetMapping("/search/{id}")
    public List<ConsultationResponse> getConsultationsById(@PathVariable Integer id) {
        return service.getConsultationById(id);
    }
}
