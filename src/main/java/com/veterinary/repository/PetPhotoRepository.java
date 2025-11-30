package com.veterinary.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.PetPhoto;

public interface PetPhotoRepository extends JpaRepository<PetPhoto, Integer> {

    @Query(value = "SELECT * FROM pet_photos WHERE photo_id = :id", nativeQuery = true)
    Optional<PetPhoto> getPhotoById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM pet_photos WHERE pet_id = :petId", nativeQuery = true)
    List<PetPhoto> getPhotosByPetId(@Param("petId") Integer petId);
}
