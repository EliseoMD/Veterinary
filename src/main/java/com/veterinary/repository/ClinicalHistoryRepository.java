package com.veterinary.repository;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.ClinicalHistory;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Integer> {

    @Query(value = "SELECT * FROM clinical_histories WHERE LOWER(history_id) = LOWER(:history_id);", nativeQuery = true)
    Optional<ClinicalHistory> getClinicalHistoriesById(@Param("history_id") Integer historyId);

    @Query(value = "SELECT * FROM clinical_histories WHERE LOWER(history_date) = LOWER(:history_date);", nativeQuery = true)
    Optional<ClinicalHistory> getClinicalHistoriesByDate(@Param("history_date") Date historyDate);

    Page<ClinicalHistory> findByDate(Date date, Pageable pageable);
}
