package com.veterinary.controller;

import java.util.Date;
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
import com.veterinary.dto.request.ClinicalHistoryRequest;
import com.veterinary.dto.response.ClinicalHistoryResponse;
import com.veterinary.service.ClinicalHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clinical_histories")
@Tag(name = "Clinical Histories", description = "")
@RequiredArgsConstructor
public class ClinicalHistoryController {

    private final ClinicalHistoryService service;

    @GetMapping("/search")
    public List<ClinicalHistoryResponse> getClinicalHistoriesByDate(
            @RequestParam Date date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getClinicalHistoryByDate(date, page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get clinical history by ID")
    @ApiResponse(responseCode = "200", description = "Clinical history found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ClinicalHistoryResponse.class))})
    public ClinicalHistoryResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new clinical history")
    public ResponseEntity<ClinicalHistoryResponse> create(@Valid @RequestBody ClinicalHistoryRequest req) {
        ClinicalHistoryResponse created = service.create(req);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing clinical history")
    public ClinicalHistoryResponse update(@PathVariable Integer id, @Valid @RequestBody ClinicalHistoryRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Get clinical histories by ID")
    @GetMapping("/search/{id}")
    public List<ClinicalHistoryResponse> getClinicalHistoriesById(@PathVariable Integer id) {
        return service.getClinicalHistoryById(id);
    }

    @Operation(summary = "Get clinical history by date")
    @GetMapping("/search/{date}")
    public List<ClinicalHistoryResponse> getClinicalHistoriesByDate(@PathVariable Date date) {
        return service.getClinicalHistoryByDate(date);
    }
}
