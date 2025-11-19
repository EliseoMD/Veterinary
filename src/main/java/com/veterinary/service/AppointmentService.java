package com.veterinary.service;

import java.sql.Timestamp;
import java.util.List;
import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;

public interface AppointmentService {

    AppointmentResponse findById(Integer id);

    AppointmentResponse create(AppointmentRequest req);

    AppointmentResponse update(Integer id, AppointmentRequest req);

    public List<AppointmentResponse> getAppointmentByDate(Timestamp date);

    public List<AppointmentResponse> getAppointmentById(Integer id);

    List<AppointmentResponse> getAppointmentByDate(Timestamp date, int page, int pageSize);

}
