package com.veterinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {

    @Query(value = "SELECT * FROM system_users WHERE LOWER(email) = LOWER(:email);", nativeQuery = true)
    List<SystemUser> getSystemUsersByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM system_users WHERE LOWER (user_id) = LOWER (:user_id);", nativeQuery = true)
    List<SystemUser> getSystemUsersById(@Param("user_id") Integer userId);
}
