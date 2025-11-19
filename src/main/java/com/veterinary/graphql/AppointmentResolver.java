package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.AppointmentRequest;
import com.veterinary.dto.response.AppointmentResponse;
import com.veterinary.service.AppointmentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppointmentResolver {

    private final AppointmentService appointmentService;

    @QueryMapping
    public AppointmentResponse findAppointmentById(@Argument Integer id) {
        return appointmentService.findById(id);
    }

    @MutationMapping
    public AppointmentResponse createAppointment(@Argument AppointmentRequest req) {
        return appointmentService.create(req);
    }

}
