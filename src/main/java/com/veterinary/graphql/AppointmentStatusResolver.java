package com.veterinary.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.veterinary.dto.request.AppointmentStatusRequest;
import com.veterinary.dto.response.AppointmentStatusResponse;
import com.veterinary.service.AppointmentStatusService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppointmentStatusResolver {

    private final AppointmentStatusService statusService;

    @QueryMapping
    public AppointmentStatusResponse findAppointmentStatusById(@Argument Integer id) {
        return statusService.findById(id);
    }

    @MutationMapping
    public AppointmentStatusResponse createAppointmentStatus(@Argument AppointmentStatusRequest req) {
        return statusService.create(req);
    }

}
