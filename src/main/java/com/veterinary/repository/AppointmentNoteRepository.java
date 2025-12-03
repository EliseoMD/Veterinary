package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.AppointmentNote;

public interface AppointmentNoteRepository extends JpaRepository<AppointmentNote, Integer> {

    @Query(value = "SELECT * FROM appointment_notes WHERE LOWER (note_id) = LOWER (:note_id);", nativeQuery = true)
    Optional<AppointmentNote> getAppointmentNotesById(@Param("note_id") Integer id);

    @Query(value = "SELECT * FROM appointment_notes WHERE LOWER(note_name) = LOWER(:note_name);", nativeQuery = true)
    Optional<AppointmentNote> getAppointmentNotesByName(@Param("note_name") String name);

    Page<AppointmentNote> findByName(String name, Pageable pageable);

}
