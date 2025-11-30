package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.BreedRequest;
import com.veterinary.dto.response.BreedResponse;
import com.veterinary.service.BreedService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BreedResolver {

    private final BreedService breedService;

    @QueryMapping
    public BreedResponse findBreedById(@Argument Integer id) {
        return breedService.findById(id);
    }

    @MutationMapping
    public BreedResponse createBreed(@Argument BreedRequest req) {
        return breedService.create(req);
    }
}
