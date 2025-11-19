package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.AppointmentNoteRequest;
import com.veterinary.dto.response.AppointmentNoteResponse;
import com.veterinary.service.AppointmentNoteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppointmentNoteResolver {

    private final AppointmentNoteService noteService;

    @QueryMapping
    public AppointmentNoteResponse findAppointmentNoteById(@Argument Integer id) {
        return noteService.findById(id);
    }

    @MutationMapping
    public AppointmentNoteResponse createAppointmentNote(@Argument AppointmentNoteRequest req) {
        return noteService.create(req);
    }

}
