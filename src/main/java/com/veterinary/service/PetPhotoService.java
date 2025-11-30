package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.PetPhotoRequest;
import com.veterinary.dto.response.PetPhotoResponse;

public interface PetPhotoService {

    PetPhotoResponse findById(Integer id);

    PetPhotoResponse create(PetPhotoRequest req);

    PetPhotoResponse update(Integer id, PetPhotoRequest req);

    List<PetPhotoResponse> getPhotosByPetId(Integer petId);

}
