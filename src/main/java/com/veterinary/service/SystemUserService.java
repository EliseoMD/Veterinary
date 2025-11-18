package com.veterinary.service;

import java.util.List;

import com.veterinary.dto.request.SystemUserRequest;
import com.veterinary.dto.response.SystemUserResponse;

public interface SystemUserService {

    SystemUserResponse findById(Integer id);

    SystemUserResponse create(SystemUserRequest req);

    SystemUserResponse update(Integer id, SystemUserRequest req);

    public List<SystemUserResponse> getSystemUserByEmail(String email);

    public List<SystemUserResponse> getSystemUserById(Integer id);

    List<SystemUserResponse> findAll(int page, int pageSize);
}
