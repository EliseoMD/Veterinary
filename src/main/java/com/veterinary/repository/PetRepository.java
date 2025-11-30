package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query(value = "SELECT * FROM pets WHERE LOWER(pet_name) = LOWER(:pet_name);", nativeQuery = true)
    Optional<Pet> getPetsByName(@Param("pet_name") String petName);

    @Query(value = "SELECT * FROM pets WHERE LOWER (pet_id) = LOWER (:pet_id);", nativeQuery = true)
    Optional<Pet> getPetsById(@Param("pet_id") Integer petId);

    Page<Pet> findByName(String name, Pageable pageable);

}
