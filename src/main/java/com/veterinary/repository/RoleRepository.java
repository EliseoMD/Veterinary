package com.veterinary.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.veterinary.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT * FROM system_roles WHERE LOWER(role_name) = LOWER(:role_name);", nativeQuery = true)
    Optional<Role> getRolesByName(@Param("role_name") String roleName);

    @Query(value = "SELECT * FROM system_roles WHERE LOWER (role_id) = LOWER (:role_id);", nativeQuery = true)
    Optional<Role> getRolesById(@Param("role_id") Integer roleId);

    Page<Role> findByName(String name, Pageable pageable);

}
