package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.SpecieRequest;
import com.veterinary.dto.response.SpecieResponse;
import com.veterinary.service.SpecieService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SpecieResolver {

    private final SpecieService specieService;

    @QueryMapping
    public SpecieResponse findSpecieById(@Argument Integer id) {
        return specieService.findById(id);
    }

    @MutationMapping
    public SpecieResponse createSpecie(@Argument SpecieRequest req) {
        return specieService.create(req);
    }

}
