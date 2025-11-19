package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Query(value = "SELECT * FROM owners WHERE LOWER(owner_id) = LOWER(:owner_id);", nativeQuery = true)
    Optional<Owner> getOwnersById(@Param("owner_id") Integer ownerId);

    @Query(value = "SELECT * FROM owners WHERE LOWER(owner_name) = LOWER(:owner_name);", nativeQuery = true)
    Optional<Owner> getOwnersByName(@Param("owner_name") String ownerName);

    Page<Owner> findByName(String name, Pageable pageable);

}
