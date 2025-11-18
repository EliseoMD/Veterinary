package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.veterinary.dto.request.SystemRoleRequest;
import com.veterinary.dto.response.SystemRoleResponse;
import com.veterinary.service.SystemRoleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SystemRoleResolver {

    private final SystemRoleService roleService;

    @QueryMapping
    public SystemRoleResponse findRoleById(@Argument Integer id) {
        return roleService.findById(id);
    }

    @MutationMapping
    public SystemRoleResponse createRole(@Argument SystemRoleRequest req) {
        return roleService.create(req);
    }

}
