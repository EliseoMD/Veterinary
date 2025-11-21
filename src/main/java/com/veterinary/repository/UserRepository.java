package com.veterinary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM system_users WHERE LOWER(email) = LOWER(:email);", nativeQuery = true)
    Optional<User> getUsersByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM system_users WHERE LOWER (user_id) = LOWER (:user_id);", nativeQuery = true)
    Optional<User> getUsersById(@Param("user_id") Integer id);
}
