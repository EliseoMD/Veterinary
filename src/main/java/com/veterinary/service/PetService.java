package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.PetRequest;
import com.veterinary.dto.response.PetResponse;

public interface PetService {

    PetResponse findById(Integer id);

    PetResponse create(PetRequest req);

    PetResponse update(Integer id, PetRequest req);

    public List<PetResponse> getPetByName(String name);

    public List<PetResponse> getPetById(Integer id);

    List<PetResponse> getPetByName(String name, int page, int pageSize);

}
