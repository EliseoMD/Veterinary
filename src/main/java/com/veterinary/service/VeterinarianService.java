package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.VeterinarianRequest;
import com.veterinary.dto.response.VeterinarianResponse;

public interface VeterinarianService {

    VeterinarianResponse findById(Integer id);

    VeterinarianResponse create(VeterinarianRequest req);

    VeterinarianResponse update(Integer id, VeterinarianRequest req);

    public List<VeterinarianResponse> getVeterinarianByName(String name);

    public List<VeterinarianResponse> getVeterinarianById(Integer id);

    List<VeterinarianResponse> getVeterinarianByName(String name, int page, int pageSize);

}
