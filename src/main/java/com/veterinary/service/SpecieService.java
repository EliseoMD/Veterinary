package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.SpecieRequest;
import com.veterinary.dto.response.SpecieResponse;

public interface SpecieService {

    SpecieResponse findById(Integer id);

    SpecieResponse create(SpecieRequest req);

    SpecieResponse update(Integer id, SpecieRequest req);

    public List<SpecieResponse> getSpecieByName(String name);

    public List<SpecieResponse> getSpecieById(Integer id);

    List<SpecieResponse> getSpecieByName(String name, int page, int pageSize);

}
