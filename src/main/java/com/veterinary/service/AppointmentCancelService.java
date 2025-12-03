package com.veterinary.service;

import java.util.List;

import com.veterinary.dto.request.AppointmentCancelRequest;
import com.veterinary.dto.response.AppointmentCancelResponse;

public interface AppointmentCancelService {

    AppointmentCancelResponse findById(Integer id);

    AppointmentCancelResponse create(AppointmentCancelRequest req);

    AppointmentCancelResponse update(Integer id, AppointmentCancelRequest req);

    public List<AppointmentCancelResponse> getCancelById(Integer id);

}
