package com.veterinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.SystemRole;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Integer> {
    
    @Query(value = "SELECT * FROM system_roles WHERE LOWER(role_name) = LOWER(:role_name);", nativeQuery = true)
    List<SystemRole> getRolesByName(@Param("role_name") String roleName);

    @Query(value = "SELECT * FROM system_roles WHERE LOWER (role_id) = LOWER (:role_id);", nativeQuery = true)
    List<SystemRole> getRolesById(@Param("role_id") Integer roleId);

}
