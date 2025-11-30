package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Specie;

public interface SpecieRepository extends JpaRepository<Specie, Integer> {

    @Query(value = "SELECT * FROM species WHERE LOWER(specie_name) = LOWER(:specie_name);", nativeQuery = true)
    Optional<Specie> getSpeciesByName(@Param("specie_name") String specieName);

    @Query(value = "SELECT * FROM species WHERE LOWER(specie_id) = LOWER(:specie_id);", nativeQuery = true)
    Optional<Specie> getSpeciesById(@Param("specie_id") Integer specieId);

    Page<Specie> findByName(String name, Pageable pageable);

}
