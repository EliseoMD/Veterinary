package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.TreatmentRequest;
import com.veterinary.dto.response.TreatmentResponse;

public interface TreatmentService {

    TreatmentResponse findById(Integer id);

    TreatmentResponse create(TreatmentRequest req);

    TreatmentResponse update(Integer id, TreatmentRequest req);

    public List<TreatmentResponse> getTreatmentByName(String name);

    public List<TreatmentResponse> getTreatmentById(Integer id);

    List<TreatmentResponse> getTreatmentByName(String name, int page, int pageSize);

}
