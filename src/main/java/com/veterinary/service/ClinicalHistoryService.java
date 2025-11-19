package com.veterinary.service;

import java.util.List;
import java.util.Date;
import com.veterinary.dto.request.ClinicalHistoryRequest;
import com.veterinary.dto.response.ClinicalHistoryResponse;

public interface ClinicalHistoryService {

    ClinicalHistoryResponse findById(Integer id);

    ClinicalHistoryResponse create(ClinicalHistoryRequest req);

    ClinicalHistoryResponse update(Integer id, ClinicalHistoryRequest req);

    public List<ClinicalHistoryResponse> getClinicalHistoryById(Integer id);

    public List<ClinicalHistoryResponse> getClinicalHistoryByDate(Date date);

    List<ClinicalHistoryResponse> getClinicalHistoryByDate(Date date, int page, int pageSize);
}
