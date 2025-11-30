package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.BreedRequest;
import com.veterinary.dto.response.BreedResponse;

public interface BreedService {

    BreedResponse findById(Integer id);

    BreedResponse create(BreedRequest req);

    BreedResponse update(Integer id, BreedRequest req);

    public List<BreedResponse> getBreedByName(String name);

    public List<BreedResponse> getBreedById(Integer id);

    List<BreedResponse> getBreedByName(String name, int page, int pageSize);
}
