package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.ClinicalHistoryRequest;
import com.veterinary.dto.response.ClinicalHistoryResponse;
import com.veterinary.service.ClinicalHistoryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClinicalHistoryResolver {

    private final ClinicalHistoryService historyService;

    @QueryMapping
    public ClinicalHistoryResponse findClinicalHistoryById(@Argument Integer id) {
        return historyService.findById(id);
    }

    @MutationMapping
    public ClinicalHistoryResponse createClinicalHistory(@Argument ClinicalHistoryRequest req) {
        return historyService.create(req);
    }

}
