package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.AppointmentStatus;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Integer> {

    @Query(value = "SELECT * FROM appointment_status WHERE LOWER(status_name) = LOWER(:status_name);", nativeQuery = true)
    Optional<AppointmentStatus> getAppointmentStatusByName(@Param("status_name") String name);

    @Query(value = "SELECT * FROM appointment_status WHERE LOWER (status_id) = LOWER (:status_id);", nativeQuery = true)
    Optional<AppointmentStatus> getAppointmentStatusById(@Param("status_id") Integer id);

    Page<AppointmentStatus> findByName(String name, Pageable pageable);
}
