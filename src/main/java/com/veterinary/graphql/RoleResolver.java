package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.RoleRequest;
import com.veterinary.dto.response.RoleResponse;
import com.veterinary.service.RoleService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoleResolver {

    private final RoleService roleService;

    @QueryMapping
    public RoleResponse findRoleById(@Argument Integer id) {
        return roleService.findById(id);
    }

    @MutationMapping
    public RoleResponse createRole(@Argument RoleRequest req) {
        return roleService.create(req);
    }

}
