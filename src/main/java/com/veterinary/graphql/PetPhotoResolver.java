package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.veterinary.dto.request.PetPhotoRequest;
import com.veterinary.dto.response.PetPhotoResponse;
import com.veterinary.service.PetPhotoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PetPhotoResolver {

    private final PetPhotoService petService;

    @QueryMapping
    public PetPhotoResponse findPetPhotoById(@Argument Integer id) {
        return petService.findById(id);
    }

    @MutationMapping
    public PetPhotoResponse createPetPhoto(@Argument PetPhotoRequest req) {
        return petService.create(req);
    }

}
