package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.AppointmentStatusRequest;
import com.veterinary.dto.response.AppointmentStatusResponse;

public interface AppointmentStatusService {

    AppointmentStatusResponse findById(Integer id);

    AppointmentStatusResponse create(AppointmentStatusRequest req);

    AppointmentStatusResponse update(Integer id, AppointmentStatusRequest req);

    public List<AppointmentStatusResponse> getAppointmentStatusByName(String name);

    public List<AppointmentStatusResponse> getAppointmentStatusById(Integer id);

    List<AppointmentStatusResponse> getAppointmentStatusByName(String name, int page, int pageSize);
}
