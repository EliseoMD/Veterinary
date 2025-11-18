package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.veterinary.dto.request.SystemUserRequest;
import com.veterinary.dto.response.SystemUserResponse;
import com.veterinary.service.SystemUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SystemUserResolver {

    private final SystemUserService userService;

    @QueryMapping
    public SystemUserResponse findUserById(@Argument Integer id) {
        return userService.findById(id);
    }

    @MutationMapping
    public SystemUserResponse createUser(@Argument SystemUserRequest req) {
        return userService.create(req);
    }

}
