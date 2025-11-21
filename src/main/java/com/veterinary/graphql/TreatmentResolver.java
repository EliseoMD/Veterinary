package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.veterinary.dto.request.TreatmentRequest;
import com.veterinary.dto.response.TreatmentResponse;
import com.veterinary.service.TreatmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TreatmentResolver {

    private final TreatmentService treatmentService;

    @QueryMapping
    public TreatmentResponse findTreatmentById(@Argument Integer id) {
        return treatmentService.findById(id);
    }

    @MutationMapping
    public TreatmentResponse createTreatment(@Argument TreatmentRequest req) {
        return treatmentService.create(req);
    }
}

