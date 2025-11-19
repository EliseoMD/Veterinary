package com.veterinary.service;

import java.util.List;
import com.veterinary.dto.request.AppointmentNoteRequest;
import com.veterinary.dto.response.AppointmentNoteResponse;

public interface AppointmentNoteService {

    AppointmentNoteResponse findById(Integer id);

    AppointmentNoteResponse create(AppointmentNoteRequest req);

    AppointmentNoteResponse update(Integer id, AppointmentNoteRequest req);

    public List<AppointmentNoteResponse> getAppointmentNoteById(Integer id);

    public List<AppointmentNoteResponse> getAppointmentNoteByName(String name);

    List<AppointmentNoteResponse> getAppointmentNoteByName(String name, int page, int pageSize);

}
