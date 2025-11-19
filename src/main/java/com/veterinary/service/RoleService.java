package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.RoleRequest;
import com.veterinary.dto.response.RoleResponse;

public interface RoleService {

    RoleResponse findById(Integer id);

    RoleResponse create(RoleRequest req);

    RoleResponse update(Integer id, RoleRequest req);

    public List<RoleResponse> getRoleByName(String name);

    public List<RoleResponse> getRoleById(Integer id);

    List<RoleResponse> getRoleByName(String name, int page, int pageSize);

}
