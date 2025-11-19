package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.ConsultationRequest;
import com.veterinary.dto.response.ConsultationResponse;
import com.veterinary.service.ConsultationService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ConsultationResolver {

    private final ConsultationService consultationService;

    @QueryMapping
    public ConsultationResponse findConsultationById(@Argument Integer id) {
        return consultationService.findById(id);
    }

    @MutationMapping
    public ConsultationResponse createConsultation(@Argument ConsultationRequest req) {
        return consultationService.create(req);
    }

}
