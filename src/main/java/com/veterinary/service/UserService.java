package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.UserRequest;
import com.veterinary.dto.response.UserResponse;

public interface UserService {

    UserResponse findById(Integer id);

    UserResponse create(UserRequest req);

    UserResponse update(Integer id, UserRequest req);

    public List<UserResponse> getUserByEmail(String email);

    public List<UserResponse> getUserById(Integer id);

    List<UserResponse> getUserByEmail(String email, int page, int pageSize);

}
