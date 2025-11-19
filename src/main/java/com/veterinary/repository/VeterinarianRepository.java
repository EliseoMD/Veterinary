package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Veterinarian;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Integer> {

    @Query(value = "SELECT * FROM veterinarians WHERE LOWER(veterinarian_name) = LOWER(:veterinarian_name);", nativeQuery = true)
    Optional<Veterinarian> getVeterinariansByName(@Param("veterinarian_name") String veterinarianName);

    @Query(value = "SELECT * FROM veterinarians WHERE LOWER(veterinarian_id) = LOWER(:veterinarian_id);", nativeQuery = true)
    Optional<Veterinarian> getVeterinariansById(@Param("veterinarian_id") Integer veterinarianId);

    Page<Veterinarian> findByName(String name, Pageable pageable);

}
