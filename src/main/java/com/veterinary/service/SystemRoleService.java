package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.SystemRoleRequest;
import com.veterinary.dto.response.SystemRoleResponse;

public interface SystemRoleService {

    SystemRoleResponse findById(Integer id);

    SystemRoleResponse create(SystemRoleRequest req);

    SystemRoleResponse update(Integer id, SystemRoleRequest req);

    public List<SystemRoleResponse> getRoleByName(String name);

    public List<SystemRoleResponse> getRoleById(Integer id);

    List<SystemRoleResponse> findAll(int page, int pageSize);

}
