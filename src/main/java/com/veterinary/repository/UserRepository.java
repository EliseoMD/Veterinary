package com.veterinary.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.veterinary.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM users WHERE LOWER(email) = LOWER(:email);", nativeQuery = true)
    Optional<Users> getUsersByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE LOWER (user_id) = LOWER (:user_id);", nativeQuery = true)
    Optional<Users> getUsersById(@Param("user_id") Integer id);

    Page<Users> findByEmail(String email, Pageable pageable);
}
