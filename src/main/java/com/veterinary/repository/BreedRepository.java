package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Breed;

public interface BreedRepository extends JpaRepository<Breed, Integer> {

    @Query(value = "SELECT * FROM breeds WHERE LOWER(breed_name) = LOWER(:breed_name);", nativeQuery = true)
    Optional<Breed> getBreedsByName(@Param("breed_name") String breedName);

    @Query(value = "SELECT * FROM breeds WHERE LOWER (breed_id) = LOWER (:breed_id);", nativeQuery = true)
    Optional<Breed> getBreedsById(@Param("breed_id") Integer breedId);

    Page<Breed> findByName(String name, Pageable pageable);
}
