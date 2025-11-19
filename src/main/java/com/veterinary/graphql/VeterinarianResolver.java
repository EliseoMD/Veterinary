package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.VeterinarianRequest;
import com.veterinary.dto.response.VeterinarianResponse;
import com.veterinary.service.VeterinarianService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class VeterinarianResolver {

    private final VeterinarianService veterinarianService;

    @QueryMapping
    public VeterinarianResponse findVeterinarianById(@Argument Integer id) {
        return veterinarianService.findById(id);
    }

    @MutationMapping
    public VeterinarianResponse createVeterinarian(@Argument VeterinarianRequest req) {
        return veterinarianService.create(req);
    }

}
