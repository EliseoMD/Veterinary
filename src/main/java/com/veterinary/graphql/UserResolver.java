package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.UserRequest;
import com.veterinary.dto.response.UserResponse;
import com.veterinary.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserResolver {

    private final UserService userService;

    @QueryMapping
    public UserResponse findUserById(@Argument Integer id) {
        return userService.findById(id);
    }

    @MutationMapping
    public UserResponse createUser(@Argument UserRequest req) {
        return userService.create(req);
    }

}
