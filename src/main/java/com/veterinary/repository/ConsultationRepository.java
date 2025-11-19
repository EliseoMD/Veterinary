package com.veterinary.repository;

import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    @Query(value = "SELECT * FROM consultations WHERE LOWER(consultation_id) = LOWER(:consultation_id);", nativeQuery = true)
    Optional<Consultation> getConsultationsById(@Param("consultation_id") Integer consultationId);

    @Query(value = "SELECT * FROM consultations WHERE LOWER (consultation_date) = LOWER (:consultation_date);", nativeQuery = true)
    Optional<Consultation> getConsultationsByDate(@Param("consultation_date") Timestamp consultationDate);

    Page<Consultation> findByDate(Timestamp date, Pageable pageable);

}
