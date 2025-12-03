package com.veterinary.service;

import java.time.LocalDateTime;
import java.util.List;
import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;

public interface AppointmentService {

    AppointmentResponse findById(Integer id);

    AppointmentResponse create(AppointmentRequest req);

    AppointmentResponse update(Integer id, AppointmentRequest req);

    public List<AppointmentResponse> getAppointmentByDate(LocalDateTime date);

    public List<AppointmentResponse> getAppointmentById(Integer id);

    List<AppointmentResponse> getAppointmentByDate(LocalDateTime date, int page, int pageSize);

}
