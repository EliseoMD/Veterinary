package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.OwnerRequest;
import com.veterinary.dto.response.OwnerResponse;
import com.veterinary.service.OwnerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OwnerResolver {

    private final OwnerService ownerService;

    @QueryMapping
    public OwnerResponse findOwnerById(@Argument Integer id) {
        return ownerService.findById(id);
    }

    @MutationMapping
    public OwnerResponse createOwner(@Argument OwnerRequest req) {
        return ownerService.create(req);
    }
}
