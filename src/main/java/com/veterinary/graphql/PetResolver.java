package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.PetRequest;
import com.veterinary.dto.response.PetResponse;
import com.veterinary.service.PetService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PetResolver {

    private final PetService petService;

    @QueryMapping
    public PetResponse findPetById(@Argument Integer id) {
        return petService.findById(id);
    }

    @MutationMapping
    public PetResponse createPet(@Argument PetRequest req) {
        return petService.create(req);
    }

}
