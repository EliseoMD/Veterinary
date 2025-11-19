package com.veterinary.service;

import java.sql.Timestamp;
import java.util.List;
import com.veterinary.dto.request.ConsultationRequest;
import com.veterinary.dto.response.ConsultationResponse;

public interface ConsultationService {

    ConsultationResponse findById(Integer consultationId);

    ConsultationResponse create(ConsultationRequest req);

    ConsultationResponse update(Integer consultationId, ConsultationRequest req);

    public List<ConsultationResponse> getConsultationByDate(Timestamp date);

    public List<ConsultationResponse> getConsultationById(Integer consultationId);

    List<ConsultationResponse> getConsultationByDate(Timestamp date, int page, int pageSize);

}
