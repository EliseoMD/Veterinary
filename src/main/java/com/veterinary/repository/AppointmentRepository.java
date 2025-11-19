package com.veterinary.repository;

import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = "SELECT * FROM appointments WHERE LOWER (appointment_date) = LOWER (:appointment_date);", nativeQuery = true)
    Optional<Appointment> getAppointmentByDate(@Param("appointment_date") Timestamp appointmentDate);

    @Query(value = "SELECT * FROM appointments WHERE LOWER (appointment_id) = LOWER (:appointment_id);", nativeQuery = true)
    Optional<Appointment> getAppointmentById(@Param("appointment_id") Integer appointmentId);

    Page<Appointment> findByDate(Timestamp date, Pageable pageable);

}
