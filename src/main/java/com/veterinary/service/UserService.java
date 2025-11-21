package com.veterinary.service;

import java.util.List;

import com.veterinary.dto.request.UserRequest;
import com.veterinary.dto.response.UserResponse;

public interface UserService {

    UserResponse findById(Integer id);

    UserResponse create(UserRequest req);

    UserResponse update(Integer id, UserRequest req);

    public List<UserResponse> getUserByEmail(String name);

    public List<UserResponse> getUserById(Integer id);

    List<UserResponse> findAll(int page, int pageSize);

}
