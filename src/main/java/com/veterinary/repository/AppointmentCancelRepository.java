package com.veterinary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.AppointmentCancel;

public interface AppointmentCancelRepository extends JpaRepository<AppointmentCancel, Integer> {

    @Query(value = "SELECT * FROM appointment_cancel WHERE cancel_id = :id", nativeQuery = true)
    Optional<AppointmentCancel> getCancelById(@Param("id") Integer id);
}
