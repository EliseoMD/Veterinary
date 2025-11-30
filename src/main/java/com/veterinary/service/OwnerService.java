package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.OwnerRequest;
import com.veterinary.dto.response.OwnerResponse;

public interface OwnerService {

    OwnerResponse findById(Integer id);

    OwnerResponse create(OwnerRequest req);

    OwnerResponse update(Integer id, OwnerRequest req);

    public List<OwnerResponse> getOwnerByName(String name);

    public List<OwnerResponse> getOwnerById(Integer id);

    List<OwnerResponse> getOwnerByName(String name, int page, int pageSize);

}
