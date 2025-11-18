package com.veterinary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    @Query(value = "SELECT * FROM treatments WHERE LOWER(treatment_name) = LOWER(:treatment_name);", nativeQuery = true)
    List<Treatment> getTreatmentsByName(@Param("treatment_name") String name);

    @Query(value = "SELECT * FROM treatments WHERE LOWER (treatment_id) = LOWER (:treatment_id);", nativeQuery = true)
    List<Treatment> getTreatmentsById(@Param("treatment_id") Integer id);

     Page<Treatment> findByName(String name, Pageable pageable);
}
